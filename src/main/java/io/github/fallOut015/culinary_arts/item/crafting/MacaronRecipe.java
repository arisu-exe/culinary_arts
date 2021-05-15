package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.item.ColoredItem;
import io.github.fallOut015.culinary_arts.item.MultiColoredItem;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.tags.ItemTagsCulinaryArts;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MacaronRecipe extends SpecialRecipe {
    public MacaronRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World level) {
        int column = -1;
        for(int i = 0; i < inventory.getWidth(); ++ i) {
            if(inventory.getItem(i).getItem() == ItemsCulinaryArts.MACARON_SHELL.get()) {
                if(column == -1) {
                    column = i;
                } else {
                    return false;
                }
            }
        }
        if(column == -1) {
            return false;
        }
        ItemStack middle = inventory.getItem(column + inventory.getWidth());
        ItemStack bottom = inventory.getItem(column + 2 * inventory.getWidth());
        if(middle.getItem().is(ItemTagsCulinaryArts.MACARON_FILLINGS) && bottom.getItem() == ItemsCulinaryArts.MACARON_SHELL.get()) {
            return true;
        }
        return false;
        // TODO check ALL recipes. it might allow foreign items...
    }
    @Override
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack top = ItemStack.EMPTY, middle = ItemStack.EMPTY, bottom = ItemStack.EMPTY;
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            if(inventory.getItem(i).getItem() == ItemsCulinaryArts.MACARON_SHELL.get()) {
                if(top.isEmpty()) {
                    top = inventory.getItem(i);
                } else {
                    bottom = inventory.getItem(i);
                }
            } else if(inventory.getItem(i).getItem().is(ItemTagsCulinaryArts.MACARON_FILLINGS)) {
                middle = inventory.getItem(i);
            }
        }
        ItemStack macaron = new ItemStack(ItemsCulinaryArts.MACARON.get());
        CompoundNBT tag = macaron.getOrCreateTag();
        tag.putInt("topColor", MultiColoredItem.getColor(top));
        tag.putInt("middleColor", middle.getItem() instanceof MultiColoredItem ? MultiColoredItem.getColor(middle) : ColoredItem.getColor(middle));
        tag.putInt("bottomColor", MultiColoredItem.getColor(bottom));
        return macaron;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return true;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializersCulinaryArts.MACARON.get();
    }
}