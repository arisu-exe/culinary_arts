package io.github.fallOut015.culinary_arts.item.crafting;

import com.mojang.datafixers.util.Pair;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class FunctionalRecipeTemplate {
    final List<Supplier<Ingredient>> ingredients;
    final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> optionalIngredients;
    final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> specialIngredients;
    final Item result;
    final boolean shapeless;

    private FunctionalRecipeTemplate(final List<Supplier<Ingredient>> ingredients, final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> optionalIngredients, final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> specialIngredients, final Item result, final boolean shapeless) {
        this.ingredients = ingredients;
        this.optionalIngredients = optionalIngredients;
        this.specialIngredients = specialIngredients;
        this.result = result;
        this.shapeless = shapeless;
    }

    public boolean matches(CraftingInventory inventory, World level) {
        List<Supplier<Ingredient>> clone = new LinkedList<>(this.ingredients);
        List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> specialClone = new LinkedList<>(this.specialIngredients);

        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            ItemStack itemStack = inventory.getItem(i);

            if(itemStack == ItemStack.EMPTY) {
                continue;
            }

            boolean noneWorked = true;

            LinkedList<Integer> removes = new LinkedList<>();
            for(int j = 0; j < clone.size(); ++ j) {
                Supplier<Ingredient> ingredient = clone.get(j);
                if(ingredient.get().test(itemStack)) {
                    removes.add(j);
                    noneWorked = false;
                }
            }
            removes.forEach(r -> clone.remove((int) r));
            removes.clear();

            for(int j = 0; j < specialClone.size(); ++ j) {
                Supplier<Ingredient> ingredient = specialClone.get(j).getFirst();
                if(ingredient.get().test(itemStack)) {
                    removes.add(j);
                    noneWorked = false;
                }
            }
            removes.forEach(r -> specialClone.remove((int) r));

            if(noneWorked) {
                return false;
            }
        }
        return clone.isEmpty() && specialClone.isEmpty();
    }
    public ItemStack assemble(CraftingInventory inventory) {
        ItemStack result = new ItemStack(this.result);
        for(Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>> specialIngredient : this.specialIngredients) {
            specialIngredient.getSecond().accept(getIngredient(specialIngredient.getFirst().get(), inventory), result);
        }
        return result;
    }

    private static ItemStack getIngredient(Ingredient ingredient, CraftingInventory inventory) {
        for(int i = 0; i < inventory.getContainerSize(); ++ i) {
            if(ingredient.test(inventory.getItem(i))) {
                return inventory.getItem(i);
            }
        }
        return ItemStack.EMPTY;
    }

    public static class Builder {
        final List<Supplier<Ingredient>> ingredients;
        final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> optionalIngredients;
        final List<Pair<Supplier<Ingredient>, BiConsumer<ItemStack, ItemStack>>> specialIngredients;
        Item result;
        boolean shapeless;

        public Builder() {
            this.ingredients = new LinkedList<>();
            this.optionalIngredients = new LinkedList<>();
            this.specialIngredients = new LinkedList<>();
            this.shapeless = true;
        }

        public Builder requires(Item... ingredients) {
            return this.requires((Supplier<Ingredient>[]) (Arrays.stream(ingredients).map(item -> (Supplier<Ingredient>) (() -> Ingredient.of(item))).toArray(Supplier[]::new)));
        }
        public Builder requires(Supplier<Ingredient>... ingredients) {
            this.ingredients.addAll(Arrays.asList(ingredients));
            return this;
        }
        public Builder special(Supplier<Ingredient> ingredient, BiConsumer<ItemStack, ItemStack> consumer) {
            this.specialIngredients.add(Pair.of(ingredient, consumer));
            return this;
        }
        public Builder optionalAny(Supplier<Ingredient> ingredient, BiConsumer<ItemStack, ItemStack> consumer) { // if provided with a tag, only allow one to be used
            //this.specialIngredients.add(Pair.of(ingredient, consumer));
            return this;
        }
        public Builder optionalMulti(Supplier<Ingredient> ingredient, BiConsumer<ItemStack, ItemStack> consumer) { // if provided with a tag, allow any number to be used
            //this.specialIngredients.add(Pair.of(ingredient, consumer));
            return this;
        }
        // one of method (supply a list and only let one be used)
        public Builder setResult(Item result) {
            this.result = result;
            return this;
        }

        public Builder shaped() {
            this.shapeless = false;
            return this;
        }

        final FunctionalRecipeTemplate build() {
            return new FunctionalRecipeTemplate(this.ingredients, this.optionalIngredients, this.specialIngredients, this.result, this.shapeless);
        }
    }
}