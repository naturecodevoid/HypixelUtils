package dev.naturecodevoid.forge.hypixelutils.base;

import dev.naturecodevoid.forge.hypixelutils.HypixelUtils;
import dev.naturecodevoid.forge.hypixelutils.gui.EditorGui;
import dev.naturecodevoid.forge.hypixelutils.util.DrawUtils;
import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.function.Function;

public abstract class GuiFeatureEditor extends GuiScreen {
    // 0: on press; 1: draw screen
    public final HashMap<GuiButton, Runnable[]> buttons = new HashMap<GuiButton, Runnable[]>();
    public final int startHeightButtonAdd = 25;
    public String title;
    public int startHeight = 35; //81;
    public boolean showEnabled = true;

    public GuiFeature getFeature() {
        throw new NotImplementedException("Please override the getFeature function!");
    }

    public void makeButton(String text, Function<GuiButton, Void> onClick, Function<GuiButton, Void> onRender) {
        GuiButton btn = new GuiButton(buttons.size() + 1, this.width / 2 - 75, startHeight - 7, 150, 20, text);
        this.buttons.put(
                btn,
                new Runnable[]{
                        () -> onClick.apply(btn),
                        () -> onRender.apply(btn)
                }
        );
        startHeight += startHeightButtonAdd;
    }

    public void init() {
        {
            GuiButton btn = new GuiButton(buttons.size() + 1, 1, 1, 30, 20, "Back");
            this.buttons.put(
                    btn,
                    new Runnable[]{
                            () -> HypixelUtils.gui = new EditorGui(),
                            () -> {
                            }
                    }
            );
        }

        GuiFeature f = getFeature();

        if (showEnabled) {
            makeButton(
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
//            GuiButton btn = new GuiButton(buttons.size() + 1, this.width / 2 - 75, startHeight - 7, 150, 20, "Enabled: " + Utils.getBooleanText(f.getEnabled()));
//            this.buttons.put(
//                    btn,
//                    new Runnable[]{
//                            () -> {
//                                f.setEnabled(!(f.getEnabled()));
//                                btn.displayString = "Enabled: " + Utils.getBooleanText(f.getEnabled());
//                            },
//                            () -> {
//                                btn.displayString = "Enabled: " + Utils.getBooleanText(f.getEnabled());
//                            }
//                    });
//            startHeight += startHeightButtonAdd;
        }
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        if (mc.theWorld != null) this.drawGradientRect(0, 0, this.width, this.height, -1608507360, -1608507360);
        MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this));

        DrawUtils.drawStringWithShadow(title, this.width / 2 - DrawUtils.getStringWidth(title) / 2, 15);

        buttons.forEach((GuiButton btn, Runnable[] run) -> {
            run[1].run();
        });

        super.drawScreen(x, y, partialTicks);
    }

    public void initGui() {
        this.init();
        buttons.forEach((GuiButton button, Runnable[] ignored) -> {
            this.buttonList.add(button);
        });
    }

    public void actionPerformed(GuiButton button) {
        final boolean[] foundButton = {false};
        buttons.forEach((GuiButton btn, Runnable[] run) -> {
            if (btn.id == button.id && !foundButton[0]) {
                run[0].run();
                foundButton[0] = true;
            }
        });
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Utils.saveConfig();
    }
}
