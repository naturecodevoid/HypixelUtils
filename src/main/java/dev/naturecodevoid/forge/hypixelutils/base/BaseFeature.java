package dev.naturecodevoid.forge.hypixelutils.base;

public interface BaseFeature {
    BaseFeature instance = null;

    static BaseFeature get() {
        return instance;
    }

    boolean getEnabled();

    boolean setEnabled(boolean enabled);

    boolean isHypixel();

    boolean isGui();

    abstract class BaseMethods implements BaseFeature {
        public boolean isGui() {
            return false;
        }

        @Override
        public String toString() {
            String[] array = getClass().getName().split("\\.");
            return array[array.length - 1];
        }
    }
}

//public abstract class BaseFeature {
//    public BaseFeature instance;
//    public Integer index;
//
//    /*
//    public static CHANGEME instance;
//    public static Integer index;
//
//    public CHANGEME() {
//        instance = this;
//        MinecraftForge.EVENT_BUS.register(this);
//    }
//    */
//
//    public BaseFeature(int index) {
//        this.index = index;
//    }
//
//    public static BaseFeature get() {
//        return HypixelUtils.features.get(index);
//    }
//
//    public boolean getEnabled() {
//        throw new NotImplementedException("Please override the getEnabled function! This checks if the feature is enabled.");
//    }
//
//    public boolean isHypixel() {
//        throw new NotImplementedException("Please override the isHypixel function! This checks if the feature is hypixel only.");
//    }
//
//    public boolean isGui() {
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        String[] array = getClass().getName().split("\\.");
//        return array[array.length - 1];
//    }
//}
