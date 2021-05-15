package io.github.fallOut015.culinary_arts.inventory.container;

import io.github.fallOut015.culinary_arts.tileentity.CookingPotTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CookingPotSlot extends Slot {
    public CookingPotSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !this.hasItem(); // TODO only let place if boilable
    }
    // TODO when attempted to place whole stack only place one
}