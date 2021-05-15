package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

// TODO make ColoredItemRecipe class

public class MacaronageRecipe extends SpecialRecipe {
    public MacaronageRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World level) {
        boolean hasSugar = false, hasWheat = false, hasEgg = false, hasSalt = false, hasDye = false;
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            if(inventory.getItem(i).getItem() == Items.SUGAR) {
                if(hasSugar) {
                    return false;
                } else {
                    hasSugar = true;
                }
            } else if(inventory.getItem(i).getItem() == Items.WHEAT) {
                if(hasWheat) {
                    return false;
                } else {
                    hasWheat = true;
                }
            } else if(inventory.getItem(i).getItem() == Items.EGG) {
                if(hasEgg) {
                    return false;
                } else {
                    hasEgg = true;
                }
            } else if(inventory.getItem(i).getItem() == ItemsCulinaryArts.SALT.get()) {
                if(hasSalt) {
                    return false;
                } else {
                    hasSalt = true;
                }
            } else if(inventory.getItem(i).getItem() instanceof DyeItem) {
                if(hasDye) {
                    return false;
                } else {
                    hasDye = true;
                }
            }
        }
        return hasSugar && hasWheat && hasEgg && hasSalt && hasDye;
    }
    @Override
    public ItemStack assemble(CraftingInventory inventory) {
        int color = 0;
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            if(inventory.getItem(i).getItem() instanceof DyeItem) {
                color = ((DyeItem) inventory.getItem(i).getItem()).getDyeColor().getColorValue();
                break;
            }
        }
        ItemStack macaronage = new ItemStack(ItemsCulinaryArts.MACARONAGE.get());
        CompoundNBT tag = macaronage.getOrCreateTag();
        tag.putInt("color", color);
        return macaronage;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return true;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializersCulinaryArts.MACARONAGE.get();
    }
}