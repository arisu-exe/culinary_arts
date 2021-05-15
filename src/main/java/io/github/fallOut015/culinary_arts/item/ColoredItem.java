package io.github.fallOut015.culinary_arts.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ColoredItem extends Item {
    final int color;

    public ColoredItem(int color, Properties properties) {
        super(properties);
        this.color = color;
    }

    public static int getColor(ItemStack stack) {
        return ((ColoredItem) stack.getItem()).getColor();
    }

    public final int getColor() {
        return this.color;
    }
}