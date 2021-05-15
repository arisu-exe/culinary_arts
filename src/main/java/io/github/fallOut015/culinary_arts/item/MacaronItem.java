package io.github.fallOut015.culinary_arts.item;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class MacaronItem extends Item {
    public MacaronItem(Properties properties) {
        super(properties);
    }

    public static int getTopColor(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.getInt("topColor");
    }
    public static int getMiddleColor(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.getInt("middleColor");
    }
    public static int getBottomColor(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return tag.getInt("bottomColor");
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> items) {
        if(this.allowdedIn(tab)) {
            for(DyeColor dyeColor : DyeColor.values()) {
                ItemStack stack = new ItemStack(this);
                CompoundNBT tag = stack.getOrCreateTag();
                tag.putInt("topColor", dyeColor.getColorValue());
                tag.putInt("middleColor", ((dyeColor == DyeColor.WHITE ? DyeColor.BLACK : DyeColor.WHITE)).getColorValue());
                tag.putInt("bottomColor", dyeColor.getColorValue());
                items.add(stack);
            }
        }
    }
}