package dev.naturecodevoid.forge.hypixelutils.gui;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GeneralGui extends GuiScreen {
    public void initGui() {
        this.buttonList.add(new GuiButton(1,
                this.width / 2 - 75,
                this.height / 2 - 20 - 1,
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
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawDefaultBackground();

        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        String text = EnumChatFormatting.WHITE + "HypixelUtils v" + HypixelUtils.VERSION;
        fRender.drawStringWithShadow(text, ((float) this.width) / 2 - ((float) fRender.getStringWidth(text)) / 2, ((float) this.height) / 2 - 33, 0);

        super.drawScreen(x, y, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                HypixelUtils.gui = HypixelUtils.config.gui();
                return;
            case 2:
                HypixelUtils.gui = new EditorGui();
                return;
        }
    }
}
