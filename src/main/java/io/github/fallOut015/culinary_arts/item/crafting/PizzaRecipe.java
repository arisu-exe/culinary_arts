package io.github.fallOut015.culinary_arts.item.crafting;

import io.github.fallOut015.culinary_arts.item.ColoredItem;
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

import java.util.LinkedList;
import java.util.List;

public class PizzaRecipe extends SpecialRecipe {
    public PizzaRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World level) {
        boolean hasDough = false, hasSauce = false, hasCheese = false;
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            if(inventory.getItem(i).getItem() == ItemsCulinaryArts.PIZZA_DOUGH.get()) {
                if(hasDough) {
                    return false;
                } else {
                    hasDough = true;
                }
            } else if(inventory.getItem(i).getItem().is(ItemTagsCulinaryArts.SAUCES)) {
                if(hasSauce) {
                    return false;
                } else {
                    hasSauce = true;
                }
            } else if(inventory.getItem(i).getItem() == ItemsCulinaryArts.CHEESE.get()) {
                if(hasCheese) {
                    return false;
                } else {
                    hasCheese = true;
                }
            }
        }
        return hasDough && hasSauce && hasCheese;
    }
    @Override
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack sauce = ItemStack.EMPTY;
        List<Item> toppings = new LinkedList<>();
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
           if(inventory.getItem(i).getItem().is(ItemTagsCulinaryArts.SAUCES)) {
                sauce = inventory.getItem(i);
            } else if(inventory.getItem(i).getItem().is(ItemTagsCulinaryArts.PIZZA_TOPPINGS)) {
                toppings.add(inventory.getItem(i).getItem());
            }
        }
        ItemStack pizza = new ItemStack(ItemsCulinaryArts.PIZZA.get());
        CompoundNBT tag = pizza.getOrCreateTag();
        tag.putInt("sauceColor", ColoredItem.getColor(sauce));
        for(Item item : toppings) {
            tag.putString("toppings", tag.getString("toppings") + item.getRegistryName().toString() + ",");
        }
        return pizza;
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return true;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializersCulinaryArts.PIZZA.get();
    }
}