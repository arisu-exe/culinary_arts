package io.github.fallOut015.culinary_arts.item.crafting;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FunctionalRecipe extends SpecialRecipe {
    IRecipeSerializer<?> serializer;
    final FunctionalRecipeTemplate template;

    public FunctionalRecipe(ResourceLocation id, IRecipeSerializer<?> serializer, final FunctionalRecipeTemplate template) {
        super(id);
        this.serializer = serializer;
        this.template = template;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World level) {
        return this.template.matches(inventory, level);
    }
    @Override
    public ItemStack assemble(CraftingInventory inventory) {
        return this.template.assemble(inventory);
    }
    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return this.serializer;
    }
}