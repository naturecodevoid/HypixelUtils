package dev.naturecodevoid.hypixelutils.base;

import dev.naturecodevoid.hypixelutils.HypixelUtils;
import dev.naturecodevoid.hypixelutils.gui.components.ConfirmGui;
import dev.naturecodevoid.hypixelutils.util.Utils;
import dev.naturecodevoid.hypixelutils.util.Vector2;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Methods and properties GUI features need to have
 */
public interface GuiFeature extends BaseFeature {
    /**
     * The function that's called on render.
     */
    void render();

    /**
     * The function that's called on rendering in the editor GUI.
     */
    void renderEditor();

    /**
     * Resets the position.
     */
    void resetPosition();

    /**
     * Gets the position, usually from the Config.
     */
    Vector2 getPosition();

    /**
     * Sets the position, usually to the Config.
     */
    Vector2 setPosition(Vector2 percent);

    /**
     * Sets the position, usually to the Config.
     */
    Vector2 setPosition(int xPercent, int yPercent);

    /**
     * Gets the scale. This is usually a constant value.
     */
    Vector2 getSize();

    /**
     * Returns the editor class initialized with the specified back GuiScreen.
     */
    GuiEditor getEditor(GuiScreen backScreen);

    /**
     * Default methods for GUI features
     */
    abstract class GuiMethods extends BaseFeature.BaseMethods implements GuiFeature {
        public Vector2 setPosition(Vector2 percent) {
            return this.setPosition(percent.x, percent.y);
        }

        public boolean isGui() {
            return true;
        }

        @SubscribeEvent
        public void onRender(RenderGameOverlayEvent event) {
            if (Utils.getNotEnabled(event, this.isHypixel(), this.getEnabled()))
                return;

            this.render();
        }
    }

    /**
     * The base editor for GUI features; contains some utility methods
     * <p>
     * When a feature extends this the class should be private.
     */
    abstract class GuiEditor extends BaseEditor {
        /**
         * @param backScreen What GuiScreen to go to once the back button is clicked.
         */
        public GuiEditor(GuiScreen backScreen) {
            super(backScreen);
        }

        /**
         * Returns the class of the feature this editor is made for.
         */
        @Override
        public GuiFeature getFeature() {
            throw new NotImplementedException("Please override the getFeature function!");
        }

        /**
         * Initializes the editor and creates any needed buttons. Make sure to super.init()!
         */
        @Override
        public void init() {
            super.init();

            GuiFeature f = this.getFeature();

            // Reset position button

            this.makeButton(
                    EnumChatFormatting.RED + "Reset Position",
                    (GuiButton btn) -> {
                        HypixelUtils.gui = new ConfirmGui(
                                f.getEditor(this.backScreen),
                                "Are you sure you want to reset the position of " + f.displayName() + "?",
                                () -> {
                                    f.resetPosition();
                                    HypixelUtils.gui = f.getEditor(this.backScreen);
                                },
                                () -> HypixelUtils.gui = f.getEditor(this.backScreen)
                        );
                        return null;
                    },
                    (GuiButton btn) -> null
            );
        }
    }
}
