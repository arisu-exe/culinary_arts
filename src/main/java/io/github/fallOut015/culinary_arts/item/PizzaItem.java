package io.github.fallOut015.culinary_arts.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PizzaItem extends Item {
    public PizzaItem(Properties properties) {
        super(properties);
    }

    public static int getSauceColor(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.getInt("sauceColor");
    }
    public static List<Item> getToppings(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        String toppingsString = tag.getString("toppings");
        String[] toppingsArray = toppingsString.split(",");
        List<Item> toppingsList = new LinkedList<>();
        Arrays.stream(toppingsArray).forEach(name -> toppingsList.add(Registry.ITEM.get(new ResourceLocation(name))));
        return toppingsList;
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> items) {
        if(this.allowdedIn(tab)) {
            {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("sauceColor", ((ColoredItem) ItemsCulinaryArts.MARINARA_SAUCE.get()).getColor());
                items.add(stack);
            }
            {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("sauceColor", ((ColoredItem) ItemsCulinaryArts.ALFREDO_SAUCE.get()).getColor());
                items.add(stack);
            }
            {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("sauceColor", ((ColoredItem) ItemsCulinaryArts.CARBONARA_SAUCE.get()).getColor());
                items.add(stack);
            }
        }
    }
}