package io.github.fallOut015.culinary_arts.client.renderer.color;

import io.github.fallOut015.culinary_arts.item.MultiColoredItem;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.item.MacaronItem;
import io.github.fallOut015.culinary_arts.item.PizzaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class ItemColorsCulinaryArts {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        Minecraft minecraft = event.getMinecraftSupplier().get();

        for(Item item : ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof MultiColoredItem).collect(Collectors.toList())) {
            minecraft.getItemColors().register((stack, tintIndex) -> tintIndex == 0 ? MultiColoredItem.getColor(stack) : DyeColor.WHITE.getColorValue(), item);
        }
        minecraft.getItemColors().register((stack, tintIndex) -> {
            switch(tintIndex) {
                case 0:
                    return MacaronItem.getTopColor(stack);
                case 1:
                    return MacaronItem.getMiddleColor(stack);
                case 2:
                    return MacaronItem.getBottomColor(stack);
            }
            return 0;
        }, ItemsCulinaryArts.MACARON.get());
        minecraft.getItemColors().register((stack, tintIndex) -> tintIndex == 0 ? PizzaItem.getSauceColor(stack) : DyeColor.WHITE.getColorValue(), ItemsCulinaryArts.PIZZA.get());
    }
}
