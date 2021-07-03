package io.github.fallOut015.culinary_arts.item.crafting;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class FunctionalRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FunctionalRecipe> {
    BiFunction<ResourceLocation, FunctionalRecipeSerializer, FunctionalRecipe> recipe;

    public FunctionalRecipeSerializer(BiFunction<ResourceLocation, FunctionalRecipeSerializer, FunctionalRecipe> recipe) {
        this.recipe = recipe;
    }

    @Override
    public FunctionalRecipe fromJson(ResourceLocation id, JsonObject json) {
        return this.recipe.apply(id, this);
    }
    @Nullable
    @Override
    public FunctionalRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
        return this.recipe.apply(id, this);
    }
    @Override
    public void toNetwork(PacketBuffer buffer, FunctionalRecipe recipe) {
    }
}
