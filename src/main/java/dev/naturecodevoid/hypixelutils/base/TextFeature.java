package dev.naturecodevoid.hypixelutils.base;

import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Methods and properties text features need to have
 */
public interface TextFeature extends GuiFeature {
    /**
     * Gets if the feature should be surrounded in brackets, usually from the Config.
     */
    boolean getBrackets();

    /**
     * Sets if the feature should be surrounded in brackets, usually to the Config.
     */
    boolean setBrackets(boolean brackets);

    /**
     * Gets the message index, usually from the Config.
     */
    int getMessageIndex();

    /**
     * Sets the message index, usually to the Config.
     */
    int setMessageIndex(int messageIndex);

    /**
     * Gets the array of messages.
     */
    String[] getMessages();

    /**
     * Gets the array of messages, user friendly (without $1, $2, ...).
     */
    String[] getMessagesFriendly();

    /**
     * Gets the color index, usually from the Config.
     */
    int getColor();

    /**
     * Sets the color index, usually to the Config.
     */
    int setColor(int color);

    /**
     * Gets the text to be displayed. showActual determines if it should show the editor value.
     */
    String getText(boolean showActual);

    /**
     * Returns the editor class initialized with the specified back GuiScreen.
     */
    TextEditor getEditor(GuiScreen backScreen);

    /**
     * Default methods for text features
     */
    abstract class TextMethods extends GuiMethods implements TextFeature {
        public String getText() {
            return this.getText(true);
        }

        @Override
        public void render() {
            this.render(true);
        }

        public void render(boolean showActual) {
            String text = this.getText(showActual);
            Vector2 pos_ = this.getPosition();
            Vector2 pos = Utils.getPosFromPercent(pos_.x, pos_.y);

            pos.x += 4;
            pos.y += 4;

            DrawUtils.drawString(text, pos.x, pos.y);
        }

        @Override
        public void renderEditor() {
            this.render(false);
        }
    }

    /**
     * The base editor for text features; contains some utility methods
     * <p>
     * When a feature extends this the class should be private.
     */
    abstract class TextEditor extends GuiEditor {
        /**
         * If the brackets option should be shown.
         */
        public boolean showBrackets = true;
        /**
         * If the color option should be shown.
         */
        public boolean showColor = true;
        /**
         * If the message option should be shown.
         */
        public boolean showMessage = true;

        /**
         * @param backScreen What GuiScreen to go to once the back button is clicked.
         */
        public TextEditor(GuiScreen backScreen) {
            super(backScreen);
        }

        /**
         * Returns the class of the feature this editor is made for.
         */
        @Override
        public TextFeature getFeature() {
            throw new NotImplementedException("Please override the getFeature function!");
        }

        /**
         * Initializes the editor and creates any needed buttons. Make sure to super.init()!
         */
        @Override
        public void init() {
            super.init();

            TextFeature f = this.getFeature();

            // Brackets toggle

            if (showBrackets) {
                this.makeButton(
                        "Brackets: " + Utils.getBooleanText(f.getBrackets()),
                        (GuiButton btn) -> {
                            f.setBrackets(!(f.getBrackets()));
                            btn.displayString = "Brackets: " + Utils.getBooleanText(f.getBrackets());
                            return null;
                        },
                        (GuiButton btn) -> {
                            btn.displayString = "Brackets: " + Utils.getBooleanText(f.getBrackets());
                            return null;
                        }
                );
            }

            // Message switcher

            if (showMessage) {
                this.makeButton(
                        "Message: " + f.getMessagesFriendly()[f.getMessageIndex()],
                        (GuiButton btn) -> {
                            f.setMessageIndex(f.getMessageIndex() + 1);
                            btn.displayString = "Message: " + f.getMessagesFriendly()[f.getMessageIndex()];
                            return null;
                        },
                        (GuiButton btn) -> {
                            btn.displayString = "Message: " + f.getMessagesFriendly()[f.getMessageIndex()];
                            return null;
                        }
                );
            }

            // Color switcher

            if (showColor) {
                this.makeButton(
                        "Color: " + Utils.getColorText(Utils.getColor(f.getColor())),
                        (GuiButton btn) -> {
                            f.setColor(f.getColor() + 1);
                            btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(f.getColor()));
                            return null;
                        },
                        (GuiButton btn) -> {
                            btn.displayString = "Color: " + Utils.getColorText(Utils.getColor(f.getColor()));
                            return null;
                        }
                );
            }
        }


        @Override
        public void drawScreen(int x, int y, float partialTicks) {
            super.drawScreen(x, y, partialTicks);

            String text = "Preview: " + this.getFeature().getText(false);
            DrawUtils.drawString(text, this.width / 2 - DrawUtils.getPositionDifference(text, DrawUtils.TextAlignment.Middle), /*startHeight - 5*/30 + ((int) this.maxPerColumn) * 25);
        }
    }
}
