package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializersCulinaryArts {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MainCulinaryArts.MODID);



    public static RegistryObject<IRecipeSerializer<CookingPotRecipe>> COOKING_POT = RECIPE_SERIALIZERS.register("cooking_pot", CookingPotRecipe.Serializer::new);
    //public static RegistryObject<SpecialRecipeSerializer<MacaronageRecipe>> MACARONAGE = RECIPE_SERIALIZERS.register("macaronage", () -> new SpecialRecipeSerializer<>(MacaronageRecipe::new));
    public static RegistryObject<FunctionalRecipeSerializer> MACARONAGE = RECIPE_SERIALIZERS.register("macaronage", () -> new FunctionalRecipeSerializer(
        (id, serializer) -> new FunctionalRecipe(
            id,
            serializer,
            new FunctionalRecipeTemplate.Builder().requires(Items.SUGAR, Items.WHEAT, Items.EGG, ItemsCulinaryArts.SALT.get()).special(() -> Ingredient.of(Tags.Items.DYES), (ingredient, result) ->
                result.getOrCreateTag().putInt("color", (((DyeItem) ingredient.getItem()).getDyeColor()).getColorValue())
            ).setResult(ItemsCulinaryArts.MACARONAGE.get()).build()
        )
    ));
    public static RegistryObject<SpecialRecipeSerializer<MacaronRecipe>> MACARON = RECIPE_SERIALIZERS.register("macaron", () -> new SpecialRecipeSerializer<>(MacaronRecipe::new));
    public static RegistryObject<SpecialBakingRecipeSerializer<MacaronShellRecipe>> MACARON_SHELL = RECIPE_SERIALIZERS.register("macaron_shell", () -> new SpecialBakingRecipeSerializer<>(MacaronShellRecipe::new));
    public static RegistryObject<SpecialBakingRecipeSerializer<PizzaRecipe>> PIZZA = RECIPE_SERIALIZERS.register("pizza", () -> new SpecialBakingRecipeSerializer<>(PizzaRecipe::new));
    /*public static RegistryObject<FunctionalRecipeSerializer> PIZZA = RECIPE_SERIALIZERS.register("pizza", () -> new FunctionalRecipeSerializer(
        (id, serializer) -> new FunctionalRecipe(
            id,
            serializer,
            new FunctionalRecipeTemplate.Builder().requires(ItemsCulinaryArts.PIZZA_DOUGH.get(), ItemsCulinaryArts.CHEESE.get()).special(Ingredient.of(ItemTagsCulinaryArts.SAUCES), (ingredient, result) -> {
                result.getOrCreateTag().putInt("sauceColor", ((ColoredItem) ingredient.getItem()).getColor());
            }).optionalMulti(Ingredient.of(ItemTagsCulinaryArts.PIZZA_TOPPINGS), (ingredient, result) -> {
                String toppings = result.getOrCreateTag().getString("toppings");
                result.getOrCreateTag().putString("toppings", toppings + ingredient.getItem().getRegistryName().toString());
            }).setResult(ItemsCulinaryArts.PIZZA.get()).build()
        )
    ));*/



    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}