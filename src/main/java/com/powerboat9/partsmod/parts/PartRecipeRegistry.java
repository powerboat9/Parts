package com.powerboat9.partsmod.parts;

public class PartRecipeRegistry {
    private static PartRecipeRegistry instance;


    private PartRecipeRegistry() {
    }

    public static PartRecipeRegistry getRegistry() {
        if (instance == null) {
            instance = new PartRecipeRegistry();
        }
        return instance;
    }
}
