package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.item.MultiColoredItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MacaronShellRecipe extends SpecialBakingRecipe {
    public MacaronShellRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(IInventory inventory, World level) {
        return inventory.getItem(0).getItem() == ItemsCulinaryArts.MACARONAGE.get();
    }
    @Override
    public ItemStack assemble(IInventory inventory) {
        ItemStack macaronage = inventory.getItem(0);
        ItemStack macaronShell = new ItemStack(ItemsCulinaryArts.MACARON_SHELL.get(), 2);
        CompoundNBT tag = macaronShell.getOrCreateTag();
        tag.putInt("color", MultiColoredItem.getColor(macaronage));
        return macaronShell;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializersCulinaryArts.MACARON_SHELL.get();
    }
    @Override
    public int getBakingTime() {
        return 500;
    }
}