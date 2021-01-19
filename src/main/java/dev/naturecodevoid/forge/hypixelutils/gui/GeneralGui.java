package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.features.CoinTracker;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
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
                "Open GUI editor"
        ));
        this.buttonList.add(new GuiButton(2,
                this.width / 2 - 75,
                this.height / 2 + 2,
                150,
                20,
                "Open config GUI"
        ));
        this.buttonList.add(new GuiButton(3,
                this.width / 2 - 75,
                this.height / 2 + 2 + 24,
                150,
                20,
                "Use coins from last session" // Import coin tracker coins from last session
        ));
        this.buttonList.add(new GuiButton(4,
                this.width / 2 - 75,
                this.height / 2 + 2 + 24 + 24,
                150,
                20,
                "Reset coin tracker coins"
        ));
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) this.drawGradientRect(0, 0, this.width, this.height, -1608507360, -1608507360);
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));

        String text = EnumChatFormatting.GREEN + HypixelUtils.NAME + EnumChatFormatting.WHITE + " v" + HypixelUtils.VERSION;
        DrawUtils.drawStringWithShadow(text, this.width / 2 - DrawUtils.getStringWidth(text) / 2, this.height / 2 - 33);

        super.drawScreen(x, y, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                HypixelUtils.gui = new EditorGui();
                return;
            case 2:
                HypixelUtils.gui = HypixelUtils.config.gui();
                return;
            case 3:
                CoinTracker.coins = HypixelUtils.config.coinTrackerCoins;
                return;
            case 4:
                CoinTracker.coins = 0;
                Utils.saveConfig();
                return;
        }
    }
}
