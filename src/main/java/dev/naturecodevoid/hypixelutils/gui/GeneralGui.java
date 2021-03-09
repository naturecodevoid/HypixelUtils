package dev.naturecodevoid.hypixelutils.gui;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.VersionChecker;
import dev.naturecodevoid.hypixelutils.util.CustomGuiScreen;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GeneralGui extends CustomGuiScreen {
    public GeneralGui(GuiScreen backScreen) {
        super(backScreen);
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(1,
                this.width / 2 + 3,
                this.height / 2 - 20 - 2,
                74,
                20,
                "GUI Editor"
        ));

        this.buttonList.add(new GuiButton(2,
                this.width / 2 - 76,
                this.height / 2 - 20 - 2,
                74,
                20,
                "Features"
        ));

        this.buttonList.add(new GuiButton(3,
                this.width / 2 - 37,
                this.height / 2 + 2,
                74,
                20,
                "Config GUI"
        ));

        if (VersionChecker.updateAvailable)
            this.buttonList.add(new GuiButton(4,
                    this.width / 2 - 55,
                    this.height / 2 + 2 + 20 + 4,
                    110,
                    20,
                    EnumChatFormatting.GREEN + "Download update"
            ));

        super.initGui();
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);

        // HypixelUtils version text
        String text = EnumChatFormatting.GREEN + HypixelUtils.NAME + EnumChatFormatting.WHITE + " v" + HypixelUtils.VERSION + (VersionChecker.updateAvailable ? " - " + EnumChatFormatting.GREEN + "Update available!" : "");
        DrawUtils.drawString(text, this.width / 2 - DrawUtils.getPositionDifference(text, DrawUtils.TextAlignment.Middle), this.height / 2 - 34);
    }

    public void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 1:
                // GUI Editor
                HypixelUtils.gui = new EditorGui(new GeneralGui(this.backScreen));
                return;
            case 2:
                // Features
                HypixelUtils.gui = new FeatureListGui(new GeneralGui(this.backScreen));
                return;
            case 3:
                // Config GUI
                HypixelUtils.gui = HypixelUtils.config.gui();
                return;
            case 4:
                try {
                    Desktop.getDesktop().browse(new URI(VersionChecker.updateUrl));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
                return;
        }

        super.actionPerformed(button);
    }
}
