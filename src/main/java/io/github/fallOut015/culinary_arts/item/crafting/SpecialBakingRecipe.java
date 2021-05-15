package io.github.fallOut015.culinary_arts.item.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public abstract class SpecialBakingRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private ItemStack resultItem;

    public SpecialBakingRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public ItemStack getResultItem() {
        return this.resultItem;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return true;
    }
    @Override
    public IRecipeType<?> getType() {
        return RecipesTypes.BRICK_OVEN;
    }

    public abstract int getBakingTime();

    public void setResultItem(IInventory inventory) {
        this.resultItem = this.assemble(inventory);
    }
}