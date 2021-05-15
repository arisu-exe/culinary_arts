package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializersCulinaryArts {
    private static DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MainCulinaryArts.MODID);



    public static RegistryObject<IRecipeSerializer<CookingPotRecipe>> COOKING_POT = RECIPE_SERIALIZERS.register("cooking_pot", CookingPotRecipe.Serializer::new);
    public static RegistryObject<SpecialRecipeSerializer<MacaronageRecipe>> MACARONAGE = RECIPE_SERIALIZERS.register("macaronage", () -> new SpecialRecipeSerializer<>(MacaronageRecipe::new));
    public static RegistryObject<SpecialRecipeSerializer<MacaronRecipe>> MACARON = RECIPE_SERIALIZERS.register("macaron", () -> new SpecialRecipeSerializer<>(MacaronRecipe::new));
    public static RegistryObject<SpecialBakingRecipeSerializer<MacaronShellRecipe>> MACARON_SHELL = RECIPE_SERIALIZERS.register("macaron_shell", () -> new SpecialBakingRecipeSerializer<>(MacaronShellRecipe::new));
    public static RegistryObject<SpecialBakingRecipeSerializer<PizzaRecipe>> PIZZA = RECIPE_SERIALIZERS.register("pizza", () -> new SpecialBakingRecipeSerializer<>(PizzaRecipe::new));



    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}