package dev.naturecodevoid.hypixelutils.base;

import dev.naturecodevoid.hypixelutils.util.CustomGuiScreen;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.lwjgl.input.Keyboard;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Methods and properties features need to have
 */
public interface BaseFeature {
    /**
     * The instance of the feature. Should be assigned in the constructor and be static and private. Use get() to get
     * the instance.
     */
    BaseFeature instance = null;

    /**
     * Returns the features instance assigned in the constructor. If it's null something went wrong.
     */
    static BaseFeature get() {
        return instance;
    }

    /**
     * Returns the display name of the feature.
     */
    String displayName();

    /**
     * If the feature should be shown in the features list GUI.
     */
    boolean showInFeaturesList();

    /**
     * Gets if the feature is enabled, usually from the Config.
     */
    boolean getEnabled();

    /**
     * Sets if the feature is enabled and returns that value, usually to the Config.
     */
    boolean setEnabled(boolean enabled);

    /**
     * If the feature is hypixel-only.
     */
    boolean isHypixel();

    /**
     * If the feature is a GUI feature. If yes, extend/implement GuiFeature, GuiFeature.GuiMethods,
     * GuiFeature.GuiEditor
     */
    boolean isGui();

    /**
     * Returns the editor class initialized with the specified back GuiScreen.
     */
    BaseEditor getEditor(GuiScreen backScreen);

    /**
     * Default methods for features
     */
    abstract class BaseMethods implements BaseFeature {
        public boolean isGui() {
            return false;
        }

        public boolean showInFeaturesList() {
            return true;
        }

        @Override
        public String toString() {
            String[] array = getClass().getName().split("\\.");
            return array[array.length - 1];
        }
    }

    /**
     * The base editor for features; contains some utility methods
     * <p>
     * When a feature extends this the class should be private and initiated in getEditor().
     */
    abstract class BaseEditor extends CustomGuiScreen {
        /**
         * runnables: 0: on press; 1: draw screen
         */
        protected final HashMap<GuiButton, Runnable[]> buttons = new HashMap<>();
        /**
         * Amount of pixels to add for each button.
         */
        protected final int startHeightButtonAdd = 25;
        /**
         * The height to add new buttons at. After adding a button add startHeightButtonAdd to it.
         */
        protected int startHeight = 30; //81;
        /**
         * If the editor should show the enabled button.
         */
        protected boolean showEnabled = true;
        /**
         * Maximum amount of buttons per column
         */
        protected double maxPerColumn = 6;

        /**
         * @param backScreen What GuiScreen to go to once the back button is clicked.
         */
        public BaseEditor(GuiScreen backScreen) {
            super(backScreen);
        }

        /**
         * Returns the class of the feature this editor is made for.
         */
        public BaseFeature getFeature() {
            throw new NotImplementedException("Please override the getFeature function! It returns the class of the feature this editor is made for.");
        }

        /**
         * Makes a button with automatic positioning
         */
        public void makeButton(String text, Function<GuiButton, Void> onClick, Function<GuiButton, Void> onRender) {
            GuiButton btn = new GuiButton(this.buttons.size() + 1, -100, -100, 150, 20, text);
            this.buttons.put(
                    btn,
                    new Runnable[]{
                            () -> onClick.apply(btn),
                            () -> onRender.apply(btn)
                    }
            );
        }

        /**
         * Initializes the editor and creates any needed buttons. Make sure to super.init()!
         */
        public void init() {
            BaseFeature f = this.getFeature();

            if (showEnabled) {
                // Enabled button
                this.makeButton(
                        "Enabled: " + Utils.getBooleanText(f.getEnabled()),
                        (GuiButton btn) -> {
                            f.setEnabled(!(f.getEnabled()));
                            btn.displayString = "Enabled: " + Utils.getBooleanText(f.getEnabled());
                            return null;
                        },
                        (GuiButton btn) -> {
                            btn.displayString = "Enabled: " + Utils.getBooleanText(f.getEnabled());
                            return null;
                        }
                );
            }
        }

        @Override
        public void drawScreen(int x, int y, float partialTicks) {
            super.drawScreen(x, y, partialTicks);

            // Feature display name text

            String displayName = this.getFeature().displayName();
            DrawUtils.drawString(displayName, this.width / 2 - DrawUtils.getPositionDifference(displayName, DrawUtils.TextAlignment.Middle), 15);

            // Button onRender

            this.buttons.forEach((GuiButton ignored, Runnable[] run) -> {
                run[1].run();
            });
        }

        public void initGui() {
            this.init();

            // Gets the amount of columns
            final int columns = (int) Math.ceil((this.buttons.size() - 1) / this.maxPerColumn);

            // Will be assigned in following switch; holds the X positions for buttons in an array; index = column[ - 1]
            final int[] columnsX;

            switch(columns) {
                case 2:
                    columnsX = new int[]{ this.width / 2 - 150 - 5, this.width / 2 + 5 };
                    break;
                case 3:
                    columnsX = new int[]{ this.width / 2 - 75 - 150 - 5, this.width / 2 - 75, this.width / 2 + 75 + 5 };
                    break;
                // 1 or other
                default:
                    columnsX = new int[]{ this.width / 2 - 75 };
                    break;
            }

            // The current column buttons should be placed on
            int currentColumn = 0;

            int i = 0;

            Set<Entry<GuiButton, Runnable[]>> items = this.buttons.entrySet();
            List<Entry<GuiButton, Runnable[]>> sortedItems = items.stream().collect(Collectors.toList());
            Collections.sort(sortedItems, new SortById());

            for (Map.Entry<GuiButton, Runnable[]> info : sortedItems) {
                GuiButton button = info.getKey();

                // We don't want to do anything with the "Back" button except add it to the GUI
                if (button.id != this.backButtonId) {
                    // If we've put enough buttons in this column
                    if (i >= this.maxPerColumn) {
                        // Reset and move to next column
                        currentColumn++;
                        this.startHeight = 30;
                        i = 0;
                    }

                    button.xPosition = columnsX[currentColumn];
                    button.yPosition = this.startHeight;

                    this.startHeight += this.startHeightButtonAdd;

                    i++;
                }

                this.buttonList.add(button);
            }

            super.initGui();
        }

        public void actionPerformed(GuiButton button) {
            final boolean[] foundButton = { false };

            this.buttons.forEach((GuiButton btn, Runnable[] run) -> {
                if (btn.id == button.id && !foundButton[0]) {
                    run[0].run();
                    foundButton[0] = true;
                }
            });

            if (!foundButton[0] && button.id != this.backButtonId)
                System.out.println("[HypixelUtils] Failed to find button in gui " + this.toString() + " with id " + button.id + ", displayString " + button.displayString);

            super.actionPerformed(button);
        }

        @Override
        public void onGuiClosed() {
            super.onGuiClosed();
            // Save the config
            Utils.saveConfig();
            Keyboard.enableRepeatEvents(false);
        }

        /**
         * Sorts a HashMap of GuiButtons and Runnables[] by GuiButton.id
         */
        private static class SortById implements Comparator<Entry<GuiButton, Runnable[]>> {
            public int compare(Entry<GuiButton, Runnable[]> a, Entry<GuiButton, Runnable[]> b) {
                return a.getKey().id - b.getKey().id;
            }
        }
    }
}
