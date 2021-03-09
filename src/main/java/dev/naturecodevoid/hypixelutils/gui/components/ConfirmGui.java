package dev.naturecodevoid.hypixelutils.gui.components;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.util.CustomGuiScreen;
import dev.naturecodevoid.hypixelutils.util.DrawUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class ConfirmGui extends CustomGuiScreen {
    private final String confirmText;
    private final String yesText;
    private final String noText;
    private final Runnable onYes;
    private final Runnable onNo;
    private final String upperConfirmText;
    private final int spacing = 2;

    /**
     * @param backScreen  What GuiScreen to go to once the back button is clicked.
     * @param confirmText The confirmation text (example: Are you sure you want to delete?)
     * @param onYes       The runnable to run when the user clicks yes
     * @param onNo        The runnable to run when the user clicks no
     */
    public ConfirmGui(GuiScreen backScreen, String confirmText, Runnable onYes, Runnable onNo) {
        this(backScreen, confirmText, onYes, onNo, EnumChatFormatting.GREEN + "Yes", EnumChatFormatting.RED + "No", "");
    }

    /**
     * @param backScreen       What GuiScreen to go to once the back button is clicked.
     * @param confirmText      The confirmation text (example: Are you sure you want to delete?)
     * @param onYes            The runnable to run when the user clicks yes
     * @param onNo             The runnable to run when the user clicks no
     * @param upperConfirmText The text above confirmText
     */
    public ConfirmGui(GuiScreen backScreen, String confirmText, Runnable onYes, Runnable onNo, String upperConfirmText) {
        this(backScreen, confirmText, onYes, onNo, EnumChatFormatting.GREEN + "Yes", EnumChatFormatting.RED + "No", upperConfirmText);
    }

    /**
     * @param backScreen       What GuiScreen to go to once the back button is clicked.
     * @param confirmText      The confirmation text (example: Are you sure you want to delete?)
     * @param onYes            The runnable to run when the user clicks yes
     * @param onNo             The runnable to run when the user clicks no
     * @param yesText          The yes button text
     * @param noText           The no button text
     * @param upperConfirmText The text above confirmText
     */
    public ConfirmGui(GuiScreen backScreen, String confirmText, Runnable onYes, Runnable onNo, String yesText, String noText, String upperConfirmText) {
        super(backScreen);
        this.confirmText = confirmText;
        this.onYes = onYes;
        this.onNo = onNo;
        this.yesText = yesText;
        this.noText = noText;
        this.upperConfirmText = upperConfirmText;
    }

    public void initGui() {
        // this.buttonList.add(new GuiButton(1,
        //         1,
        //         1,
        //         30,
        //         20,
        //         "Back"
        // ));

        this.buttonList.add(new GuiButton(2,
                this.width / 2 - 74 - this.spacing,
                this.height / 2 + 5,
                74,
                20,
                this.yesText
        ));

        this.buttonList.add(new GuiButton(3,
                this.width / 2 + this.spacing,
                this.height / 2 + 5,
                74,
                20,
                this.noText
        ));

        super.initGui();
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);

        // Confirm text
        DrawUtils.drawString(this.confirmText, this.width / 2 - DrawUtils.getPositionDifference(this.confirmText, DrawUtils.TextAlignment.Middle), this.height / 2 - 8);

        // Upper confirm text
        if (this.upperConfirmText != null)
            if (this.upperConfirmText.length() > 0)
                DrawUtils.drawString(upperConfirmText, this.width / 2 - DrawUtils.getPositionDifference(upperConfirmText, DrawUtils.TextAlignment.Middle), this.height / 2 - 8 - 16);
    }

    public void actionPerformed(GuiButton button) {
        switch(button.id) {
            // case 1:
            //     // Back
            //     HypixelUtils.gui = backScreen;
            //     return;
            case 2:
                // Yes
                this.onYes.run();
                return;
            case 3:
                // No
                this.onNo.run();
                return;
        }

        super.actionPerformed(button);
    }
}
