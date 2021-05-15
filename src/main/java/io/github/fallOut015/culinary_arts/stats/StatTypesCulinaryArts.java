package io.github.fallOut015.culinary_arts.stats;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import net.minecraft.stats.StatType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StatTypesCulinaryArts {
    private static final DeferredRegister<StatType<?>> STAT_TYPES = DeferredRegister.create(ForgeRegistries.STAT_TYPES, MainCulinaryArts.MODID);



    public static final RegistryObject<StatType<ResourceLocation>> INTERACT_WITH_COOKING_POT = STAT_TYPES.register("interact_with_cooking_pot", () -> new StatType<>(Registry.CUSTOM_STAT));



    public static void register(IEventBus bus) {
        STAT_TYPES.register(bus);
    }
}
