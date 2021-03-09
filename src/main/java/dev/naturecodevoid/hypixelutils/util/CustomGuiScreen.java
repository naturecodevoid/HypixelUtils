package dev.naturecodevoid.hypixelutils.util;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class CustomGuiScreen extends GuiScreen {
    /*
    public CustomGuiScreen(GuiScreen backScreen) {
        super(backScreen);
    }
    */

    /**
     * The screen to return to when the back button is pressed.
     * <p>
     * If it is null, no back button will be created.
     */
    protected final GuiScreen backScreen;
    /**
     * The position of the created back button. Use this.backButtonPos.set() in initGui() before calling super.initGui()
     */
    protected final Vector2 backButtonPos = new Vector2(1,1);
    /**
     * The id of the back button.
     */
    protected final int backButtonId = 143;

    public CustomGuiScreen(GuiScreen backScreen) {
        this.backScreen = backScreen;
    }

    public void initGui() {
        if (this.backScreen != null)
            this.buttonList.add(new GuiButton(
                    backButtonId,
                    backButtonPos.x,
                    backButtonPos.y,
                    30,
                    20,
                    "Back"
            ));
    }

    public void actionPerformed(GuiButton button) {
        if (button.id == this.backButtonId && this.backScreen != null)
            HypixelUtils.gui = this.backScreen;
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) {
            this.drawGradientRect(0, 0, this.width, this.height, Utils.toHex("202020", 200), Utils.toHex("202020", 200));
            MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));
        } else this.drawDefaultBackground();

        super.drawScreen(x, y, partialTicks);
    }

    @Override
    public String toString() {
        String[] array = getClass().getName().split("\\.");
        return array[array.length - 1];
    }
}
