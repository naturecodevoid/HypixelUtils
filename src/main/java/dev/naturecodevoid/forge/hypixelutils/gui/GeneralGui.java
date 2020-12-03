package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.features.CoinTracker;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

public class GeneralGui extends GuiScreen {
    public void initGui() {
        this.buttonList.add(new GuiButton(1,
                this.width / 2 - 75,
                this.height / 2 - 20 - 2,
                150,
                20,
                "Open config GUI"
        ));
        this.buttonList.add(new GuiButton(2,
                this.width / 2 - 75,
                this.height / 2 + 2,
                150,
                20,
                "Open GUI editor"
        ));
        this.buttonList.add(new GuiButton(3,
                this.width / 2 - 75,
                this.height / 2 + 2 + 24,
                150,
                20,
                "Reset coin tracker coins"
        ));
        this.buttonList.add(new GuiButton(4,
                this.width / 2 - 75,
                this.height / 2 + 2 + 24 + 24,
                150,
                20,
                "" // Import coin tracker coins from last session
        ));
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) this.drawGradientRect(0, 0, this.width, this.height, -1608507360, -1608507360);
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));

        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        String text = EnumChatFormatting.WHITE + HypixelUtils.NAME + " v" + HypixelUtils.VERSION;
        fRender.drawStringWithShadow(text, ((float) this.width) / 2 - ((float) fRender.getStringWidth(text)) / 2, ((float) this.height) / 2 - 33, 0);

        super.drawScreen(x, y, partialTicks);

        String text2 = EnumChatFormatting.WHITE + "Import coin tracker coins";
        DrawUtils.drawString(text2, (this.width) / 2 - (DrawUtils.getStringWidth(text2)) / 2, this.height / 2 + 2 + 24 + 24 + 2);

        String text3 = EnumChatFormatting.WHITE + "from last session";
        DrawUtils.drawString(text3, (this.width) / 2 - (DrawUtils.getStringWidth(text3)) / 2, this.height / 2 + 2 + 24 + 24 + 10);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                HypixelUtils.gui = HypixelUtils.config.gui();
                return;
            case 2:
                HypixelUtils.gui = new EditorGui();
                return;
            case 3:
                CoinTracker.coins = 0;
                return;
            case 4:
                CoinTracker.coins = HypixelUtils.config.coinTrackerCoins;
                return;
        }
    }
}
