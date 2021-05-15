package io.github.fallOut015.culinary_arts.item;

import net.minecraft.item.Food;

public class FoodsCulinaryArts {
    public static final Food SOGGY_FOOD = (new Food.Builder()).nutrition(1).saturationMod(0F).build();
    public static final Food BURNT_FOOD = (new Food.Builder()).nutrition(1).saturationMod(0F).build();
    public static final Food HARD_BOILED_EGG = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final Food CHEESE = (new Food.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final Food MACARON_SHELL = (new Food.Builder()).nutrition(3).saturationMod(0.2F).build();
    public static final Food MACARON = (new Food.Builder()).nutrition(7).saturationMod(0.5F).build();

    public static final Food BANANA_BUNCH = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food BLUEBERRY = (new Food.Builder()).nutrition(1).saturationMod(0.1F).build();
    public static final Food CORN = (new Food.Builder()).nutrition(4).saturationMod(0.1F).build();
    public static final Food TOMATO = (new Food.Builder()).nutrition(2).saturationMod(0.2F).build();
    public static final Food APPLE_PIE = (new Food.Builder()).nutrition(6).saturationMod(0.4F).build();
    public static final Food SUGAR_COOKIE = new Food.Builder().nutrition(3).saturationMod(0.1f).build();
    public static final Food TOAST = (new Food.Builder()).nutrition(4).saturationMod(0.7F).build();
    public static final Food PIZZA = (new Food.Builder()).nutrition(5).saturationMod(0.7F).build();
}