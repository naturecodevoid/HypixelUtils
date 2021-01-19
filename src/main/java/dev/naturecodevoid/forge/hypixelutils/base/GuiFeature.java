package dev.naturecodevoid.forge.hypixelutils.base;

import dev.naturecodevoid.forge.hypixelutils.util.Utils;
import dev.naturecodevoid.forge.hypixelutils.util.Vector2D;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public interface GuiFeature extends BaseFeature {
    void render();

    void renderEditor();

    void resetPosition();

    Vector2D getPosition();

    Vector2D setPosition(Vector2D percent);

    Vector2D setPosition(int xPercent, int yPercent);

    Vector2D getSize();

    GuiFeatureEditor getEditor();

    abstract class GuiMethods extends BaseFeature.BaseMethods implements GuiFeature {
        public Vector2D setPosition(Vector2D percent) {
            return setPosition(percent.x, percent.y);
        }

        public boolean isGui() {
            return true;
        }

        @SubscribeEvent
        public void onRender(RenderGameOverlayEvent event) {
            if (Utils.getNotEnabled(event, isHypixel(), getEnabled()))
                return;

            render();
        }
    }
}

//public abstract class GuiFeature extends BaseFeature {
//    public GuiFeature() {
//        index = HypixelUtils.guiFeatures.size();
//        System.out.println(index + " " + toString());
//    }
//
//    public static GuiFeature get() {
//        for (GuiFeature guiFeature : HypixelUtils.guiFeatures) {
//            if (guiFeature instanceof ) {
//                return guiFeature;
//            }
//        }
//    }
//
//    public void render() {
//        throw new NotImplementedException("Please override the render function! This runs when rendering features.");
//    }
//
//    public void renderEditor() {
//        throw new NotImplementedException("Please override the renderEditor function! This runs when rendering features in the gui editor.");
//    }
//
//    public void resetPosition() {
//        throw new NotImplementedException("Please override the resetPosition function! This runs when resetting the position in the gui editor.");
//    }
//
//    public Vector2D getPosition() {
//        throw new NotImplementedException("Please override the getPosition function! This gets the feature position in percents.");
//    }
//
//    public Vector2D setPosition(Vector2D percent) {
//        return this.setPosition(percent.x, percent.y);
//    }
//
//    public Vector2D setPosition(int xPercent, int yPercent) {
//        throw new NotImplementedException("Please override the setPosition function! This sets the feature position in percents and returns getPosition().");
//    }
//
//    public Vector2D getSize() {
//        throw new NotImplementedException("Please override the getSize function! This gets the feature size.");
//    }
//
//    public GuiFeatureEditor getEditor() {
//        throw new NotImplementedException("Please override the getEditor function! This gets the feature editor.");
//    }
//
//    @Override
//    public boolean isGui() {
//        return true;
//    }
//
//    @SubscribeEvent
//    public void onRender(RenderGameOverlayEvent event) {
//        if (Utils.getNotEnabled(event, this.isHypixel(), this.getEnabled()))
//            return;
//
//        this.render();
//    }
//}
