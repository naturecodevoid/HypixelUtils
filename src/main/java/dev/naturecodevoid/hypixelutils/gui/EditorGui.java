package dev.naturecodevoid.hypixelutils.gui;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.base.GuiFeature;
import dev.naturecodevoid.hypixelutils.gui.components.ConfirmGui;
import dev.naturecodevoid.hypixelutils.util.CustomGuiScreen;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.apache.commons.lang3.NotImplementedException;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class EditorGui extends CustomGuiScreen {
    /**
     * If the user is currently dragging something.
     */
    private boolean isDragging = false;
    /**
     * The feature the user is currently dragging.
     */
    private GuiFeature currentDragging = null;
    /**
     * The last feature the user was dragging. Can be the same as currentDragging
     */
    private GuiFeature lastDragging = null;
    /**
     * The anchor position
     */
    private Vector2 anchor = null;
    /**
     * Last mouse X position
     */
    private int lastX = 0;
    /**
     * Last mouse Y position
     */
    private int lastY = 0;

    public EditorGui(GuiScreen backScreen) {
        super(backScreen);
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(1,
                this.width / 2 - 50,
                this.height - 20,
                100,
                20,
                "Reset Positions"
        ));

        this.buttonList.add(new GuiButton(2,
                -100,
                -100,
                30,
                20,
                "Edit"
        ));
        this.buttonList.get(1).visible = false;
        this.buttonList.get(1).enabled = false;

        // this.buttonList.add(new GuiButton(3,
        //         this.width / 2 - 50 - 35,
        //         this.height - 20,
        //         30,
        //         20,
        //         "Back"
        // ));

        this.backButtonPos.set(this.width / 2 - 50 - 35, this.height - 20);

        super.initGui();
    }

    public void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 1:
                // Reset position
                HypixelUtils.gui = new ConfirmGui(
                        new EditorGui(this.backScreen),
                        "If possible you should only reset the position of each feature.",
                        () -> {
                            HypixelUtils.guiFeatures.forEach(GuiFeature::resetPosition);
                            HypixelUtils.gui = new EditorGui(this.backScreen);
                        },
                        () -> HypixelUtils.gui = new EditorGui(this.backScreen),
                        "Are you sure you want to reset the positions of all GUI features?"
                );
                return;
            case 2:
                // Open the last feature's editor
                HypixelUtils.gui = this.lastDragging.getEditor(new EditorGui(this.backScreen));
                return;
            // case 3:
            //     // Go back to the general GUI
            //     HypixelUtils.gui = new GeneralGui();
            //     return;
        }

        super.actionPerformed(button);
    }

    /**
     * Utility method to check for dragging and do a lot of other things
     */
    protected void checkForDragging(int x, int y) {
        GuiFeature closestFeature = null;
        Vector2 anchor2 = null;

        if (this.currentDragging == null || Utils.distance(new Vector2(x, y), new Vector2(this.lastX, this.lastY)) >= 5) {
            for (GuiFeature f : HypixelUtils.guiFeatures) {
                Vector2 pos = Utils.getPosFromPercent(f.getPosition());
                Vector2 size = f.getSize();

                if (Utils.checkIfPosInsideRect(new Vector2(x, y), pos, new Vector2(pos.x + size.x, pos.y + size.y)) ||
                        (
                                (
                                        this.buttonList.get(1).visible ||
                                                this.buttonList.get(1).enabled
                                ) && Utils.checkIfPosInsideRect(new Vector2(x, y), pos.copy().setY(pos.y + size.y), new Vector2(pos.x + 30, pos.y + size.y + 20))
                        )) {
                    closestFeature = f;
                    anchor2 = new Vector2(x - pos.x, y - pos.y);
                    break;
                }
            }

            if (closestFeature != null) {
                this.lastDragging = closestFeature;
            }
            this.currentDragging = closestFeature;
            this.anchor = anchor2;
        }

        if (this.currentDragging != null) {
            Vector2 screenSize = Utils.getScreenSize();

            Vector2 size = this.currentDragging.getSize();

            Vector2 anchor3;

            if (this.anchor != null)
                anchor3 = this.anchor;
            else anchor3 = new Vector2(size.x / 2, size.y / 2);

            Vector2 percent = Utils.getPercentFromPos(
                    Math.min(x - anchor3.x, screenSize.x - size.x),
                    Utils.clamp(y - anchor3.y, 0, screenSize.y - size.y)
            );

            this.currentDragging.setPosition(percent);

            try {
                this.currentDragging.getEditor(new EditorGui(this.backScreen));

                Vector2 pos = this.currentDragging.getPosition();

                Vector2 newPos = Utils.getPosFromPercent(pos.x, pos.y);
                this.buttonList.get(1).xPosition = newPos.x; // + 3;
                this.buttonList.get(1).yPosition = newPos.y + size.y; // - 2;
                this.buttonList.get(1).visible = true;
                this.buttonList.get(1).enabled = true;
            } catch (NotImplementedException ignored) {
                this.buttonList.get(1).xPosition = -100;
                this.buttonList.get(1).yPosition = -100;
                this.buttonList.get(1).visible = false;
                this.buttonList.get(1).enabled = false;
            }
        } else {
            this.buttonList.get(1).xPosition = -100;
            this.buttonList.get(1).yPosition = -100;
            this.buttonList.get(1).visible = false;
            this.buttonList.get(1).enabled = false;
        }
    }

    @Override
    public void mouseClicked(int x, int y, int time) throws IOException {
        if ((y != lastY) || (x != lastX)) {
            this.isDragging = true;

            checkForDragging(x, y);

            this.lastX = x;
            this.lastY = y;
        } else {
            this.isDragging = false;
            this.currentDragging = null;
            this.buttonList.get(1).xPosition = -100;
            this.buttonList.get(1).yPosition = -100;
            this.buttonList.get(1).visible = false;
            this.buttonList.get(1).enabled = false;
        }
        super.mouseClicked(x, y, time);
    }

    @Override
    public void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceClick) {
        if (this.isDragging) {
            checkForDragging(x, y);

            this.lastX = x;
            this.lastY = y;
        } else {
            this.currentDragging = null;
            this.buttonList.get(1).xPosition = -100;
            this.buttonList.get(1).yPosition = -100;
            this.buttonList.get(1).visible = false;
            this.buttonList.get(1).enabled = false;
        }
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClick);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);

        // Render each feature.
        HypixelUtils.guiFeatures.forEach(GuiFeature::renderEditor);

        // Render the white line around each feature if it's not the one we are currently dragging.
        HypixelUtils.guiFeatures.forEach(guiFeature -> {
            if (guiFeature == this.currentDragging) return;

            Vector2 size = guiFeature.getSize();
            Vector2 pos = Utils.getPosFromPercent(guiFeature.getPosition());

            pos.y -= 1;

            int x1 = pos.x;
            int x2 = pos.x + size.x;
            int y1 = pos.y;
            int y2 = pos.y + size.y;

            drawHorizontalLine(x1, x2, y1, -1);
            drawHorizontalLine(x1, x2, y2, -1);
            drawVerticalLine(x1, y1, y2, -1);
            drawVerticalLine(x2, y1, y2, -1);
        });

        // Draw a green line around the feature we are currently dragging.
        if (this.currentDragging != null) {
            Vector2 size = this.currentDragging.getSize();
            Vector2 pos = Utils.getPosFromPercent(this.currentDragging.getPosition());

            pos.y -= 1;

            int x1 = pos.x;
            int x2 = pos.x + size.x;
            int y1 = pos.y;
            int y2 = pos.y + size.y;

            String color = "00ff19";

            drawHorizontalLine(x1, x2, y1, Utils.toHex(color));
            drawHorizontalLine(x1, x2, y2, Utils.toHex(color));
            drawVerticalLine(x1, y1, y2, Utils.toHex(color));
            drawVerticalLine(x2, y1, y2, Utils.toHex(color));
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        // Save the config
        Utils.saveConfig();
        Keyboard.enableRepeatEvents(false);
    }
}
