package io.github.fallOut015.culinary_arts.item.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class CookingPotRecipe implements IRecipe<IInventory> {
    protected final ResourceLocation id;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final int cookingTime;

    public CookingPotRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int cookingTime) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.cookingTime = cookingTime;
    }

    @Override
    public boolean matches(IInventory inventory, World level) {
        return this.ingredient.test(inventory.getItem(0));
    }
    @Override
    public ItemStack assemble(IInventory p_77572_1_) {
        return this.result.copy();
    }
    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return true;
    }
    @Override
    public ItemStack getResultItem() {
        return this.result;
    }
    @Override
    public ResourceLocation getId() {
        return this.id;
    }
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializersCulinaryArts.COOKING_POT.get();
    }
    @Override
    public IRecipeType<?> getType() {
        return RecipesTypes.COOKING_POT;
    }
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(BlocksCulinaryArts.COOKING_POT.get());
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CookingPotRecipe> {
        private static final ResourceLocation NAME = new ResourceLocation("culinary_arts", "cooking_pot");

        @Override
        public CookingPotRecipe fromJson(ResourceLocation id, JsonObject json) {
            String ingredientName = json.get("ingredient").getAsString();
            ResourceLocation resourcelocation = new ResourceLocation(ingredientName);
            Item item = Registry.ITEM.getOptional(resourcelocation).orElseThrow(() ->
                new IllegalStateException("Item: " + ingredientName + " does not exist")
            );
            Ingredient ingredient = Ingredient.of(item);
            if (!json.has("result")) {
                throw new JsonSyntaxException("Missing result, expected to find a string or object");
            }
            ItemStack result = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            int cookingTime = JSONUtils.getAsInt(json, "cookingtime", 0);
            return new CookingPotRecipe(id, ingredient, result, cookingTime);
        }
        @Override
        public CookingPotRecipe fromNetwork(ResourceLocation id, PacketBuffer packet) {
            Ingredient ingredient = Ingredient.fromNetwork(packet);
            ItemStack result = packet.readItem();
            int cookingTime = packet.readVarInt();
            return new CookingPotRecipe(id, ingredient, result, cookingTime);
        }
        @Override
        public void toNetwork(PacketBuffer packet, CookingPotRecipe recipe) {
            recipe.ingredient.toNetwork(packet);
            packet.writeItem(recipe.result);
            packet.writeVarInt(recipe.cookingTime);
        }
    }
}