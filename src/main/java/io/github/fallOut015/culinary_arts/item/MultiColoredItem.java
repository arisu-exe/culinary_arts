package io.github.fallOut015.culinary_arts.item;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class MultiColoredItem extends Item {
    public MultiColoredItem(Properties properties) {
        super(properties);
    }

    public static int getColor(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.getInt("color");
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> items) {
        if(this.allowdedIn(tab)) {
            for(DyeColor dyeColor : DyeColor.values()) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("color", dyeColor.getColorValue());
                items.add(stack);
            }
        }
    }
}