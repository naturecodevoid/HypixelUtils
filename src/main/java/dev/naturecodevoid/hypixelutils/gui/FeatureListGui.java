package dev.naturecodevoid.hypixelutils.gui;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.BaseFeature;
import dev.naturecodevoid.hypixelutils.base.GuiFeature;
import dev.naturecodevoid.hypixelutils.util.CustomGuiScreen;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.function.Function;

public class FeatureListGui extends CustomGuiScreen {
    /**
     * runnables: 0: on press; 1: draw screen
     */
    protected final HashMap<GuiButton, Runnable[]> buttons = new HashMap<GuiButton, Runnable[]>();
    protected final int startHeightButtonAdd = 25;
    protected int startHeight = 30; //81;
    protected int startWidth = 7;
    protected Vector2 guiFeaturesPos = new Vector2(-100, -100);

    public FeatureListGui(GuiScreen backScreen) {
        super(backScreen);
    }

    /**
     * Makes a button with automatic positioning
     */
    public void makeButton(String text, Function<GuiButton, Void> onClick, Function<GuiButton, Void> onRender) {
        if (this.startHeight + 30 > this.height) {
            this.startHeight = 30;
            this.startWidth += 135;
        }

        GuiButton btn = new GuiButton(this.buttons.size() + 1, this.startWidth, this.startHeight - 7, 125, 20, text);
        this.buttons.put(
                btn,
                new Runnable[]{
                        () -> onClick.apply(btn),
                        () -> onRender.apply(btn)
                }
        );
        this.startHeight += this.startHeightButtonAdd;
    }

    /**
     * Makes a button based on the feature that opens the feature's editor on press
     */
    private void makeFeature(BaseFeature feature) {
        if (feature.showInFeaturesList()) this.makeButton(
                feature.displayName(),
                (GuiButton btn) -> {
                    HypixelUtils.gui = feature.getEditor(new FeatureListGui(this.backScreen));
                    return null;
                },
                (GuiButton btn) -> null
        );
    }

    public void initGui() {
        // Loop through the normal features and add them to the list
        for (BaseFeature feature : HypixelUtils.features) {
            makeFeature(feature);
        }

        // Change the GUI feature text position to where we currently are
        this.guiFeaturesPos = new Vector2(this.startWidth + 1, this.startHeight);
        // Add more height to allow the GUI feature text to be drawn
        this.startHeight += this.startHeightButtonAdd;

        // Loop through the GUI features and add them to the list
        for (GuiFeature feature : HypixelUtils.guiFeatures) {
            this.makeFeature(feature);
        }

        this.buttons.forEach((GuiButton button, Runnable[] ignored) -> {
            this.buttonList.add(button);
        });

        this.backButtonPos.set(this.width - 1 - 30, 1);

        super.initGui();
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);

        // Features text
        String text = EnumChatFormatting.WHITE + "Features";
        DrawUtils.drawString(text, 8, 8);

        // GUI Features text
        String text2 = EnumChatFormatting.WHITE + "GUI Features";
        DrawUtils.drawString(text2, guiFeaturesPos.x, guiFeaturesPos.y);
    }

    public void actionPerformed(GuiButton button) {
        final boolean[] foundButton = { false };

        buttons.forEach((GuiButton btn, Runnable[] run) -> {
            if (btn.id == button.id && !foundButton[0]) {
                run[0].run();
                foundButton[0] = true;
            }
        });

        if (!foundButton[0] && button.id != this.backButtonId)
            System.out.println("[HypixelUtils] Failed to find button in gui " + this.toString() + " with id " + button.id + ", displayString " + button.displayString);

        super.actionPerformed(button);
    }
}
