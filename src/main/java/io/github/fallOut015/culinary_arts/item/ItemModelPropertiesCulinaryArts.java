package io.github.fallOut015.culinary_arts.item;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ItemModelPropertiesCulinaryArts {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ItemModelsProperties.register(ItemsCulinaryArts.PIZZA.get(), new ResourceLocation(MainCulinaryArts.MODID, "pizza_pepperoni"), (stack, world, entity) -> PizzaItem.getToppings(stack).contains(Items.COOKED_PORKCHOP) ? 1 : 0);
        ItemModelsProperties.register(ItemsCulinaryArts.PIZZA.get(), new ResourceLocation(MainCulinaryArts.MODID, "pizza_chicken"), (stack, world, entity) -> PizzaItem.getToppings(stack).contains(Items.COOKED_CHICKEN) ? 1 : 0);
        ItemModelsProperties.register(ItemsCulinaryArts.PIZZA.get(), new ResourceLocation(MainCulinaryArts.MODID, "pizza_anchovies"), (stack, world, entity) -> PizzaItem.getToppings(stack).contains(Items.COOKED_SALMON) ? 1 : 0);
        ItemModelsProperties.register(ItemsCulinaryArts.PIZZA.get(), new ResourceLocation(MainCulinaryArts.MODID, "pizza_mushrooms"), (stack, world, entity) -> PizzaItem.getToppings(stack).contains(Items.BROWN_MUSHROOM) ? 1 : 0);
    }
}
