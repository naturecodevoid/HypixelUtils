package dev.naturecodevoid.hypixelutils;

import java.util.Set;

import dev.naturecodevoid.hypixelutils.gui.GeneralGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

/**
 * Used to give the Config button in the mod list functionality.
 */
public class HypixelUtilsGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft mcInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return GeneralGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }
}
