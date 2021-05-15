package io.github.fallOut015.culinary_arts.item.crafting;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Function;

public class SpecialBakingRecipeSerializer<T extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    private final Function<ResourceLocation, T> constructor;

    public SpecialBakingRecipeSerializer(Function<ResourceLocation, T> constructor) {
        this.constructor = constructor;
    }

    @Override
    public T fromJson(ResourceLocation id, JsonObject json) {
        return constructor.apply(id);
    }
    @Nullable
    @Override
    public T fromNetwork(ResourceLocation id, PacketBuffer packet) {
        return constructor.apply(id);
    }
    @Override
    public void toNetwork(PacketBuffer packet, T recipe) {
    }
}