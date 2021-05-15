package io.github.fallOut015.culinary_arts.world.gen.feature;

import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeaturesCulinaryArts {
    public static final ConfiguredFeature<?, ?> ORE_SALT = register("ore_salt", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.SALTED_STONE, 17)).range(64).squared().count(20));
    public static final ConfiguredFeature<?, ?> ORE_SALT_BLOCK = register("ore_salt_block", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.SALT_BLOCK, 1)).range(64).squared().count(10));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_, ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }

    public static final class States {
        protected static final BlockState SALTED_STONE = BlocksCulinaryArts.SALTED_STONE.get().defaultBlockState();
        protected static final BlockState SALT_BLOCK = BlocksCulinaryArts.SALT_BLOCK.get().defaultBlockState();
    }

    public static void onBiomeLoading(final BiomeLoadingEvent biomeLoadingEvent) {
        if (biomeLoadingEvent.getCategory() == Biome.Category.OCEAN) {
            biomeLoadingEvent.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> FeaturesCulinaryArts.ORE_SALT);
            biomeLoadingEvent.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> FeaturesCulinaryArts.ORE_SALT_BLOCK);
        }
    }
}