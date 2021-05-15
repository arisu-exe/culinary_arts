package io.github.fallOut015.culinary_arts.item.crafting;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class RecipesTypes {
    public static final IRecipeType<CookingPotRecipe> COOKING_POT = register("cooking_pot");
    public static final IRecipeType<SpecialBakingRecipe> BRICK_OVEN = register("brick_oven");

    static <T extends IRecipe<?>> IRecipeType<T> register(final String location) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(location), new IRecipeType<T>() {
            public String toString() {
                return location;
            }
        });
    }
}