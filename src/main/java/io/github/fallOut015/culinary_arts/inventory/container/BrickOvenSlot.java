package io.github.fallOut015.culinary_arts.inventory.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class BrickOvenSlot extends Slot {
    public BrickOvenSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !this.hasItem(); // TODO only let place if boilable
    }
    // TODO when attempted to place whole stack only place one
}