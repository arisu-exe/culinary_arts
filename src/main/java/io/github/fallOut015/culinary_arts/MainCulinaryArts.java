package io.github.fallOut015.culinary_arts;

import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import io.github.fallOut015.culinary_arts.client.gui.ScreenManagerCulinaryArts;
import io.github.fallOut015.culinary_arts.client.renderer.color.ItemColorsCulinaryArts;
import io.github.fallOut015.culinary_arts.inventory.container.ContainersCulinaryArts;
import io.github.fallOut015.culinary_arts.item.ItemModelPropertiesCulinaryArts;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.item.crafting.RecipeSerializersCulinaryArts;
import io.github.fallOut015.culinary_arts.tileentity.TileEntitiesCulinaryArts;
import io.github.fallOut015.culinary_arts.world.gen.feature.FeaturesCulinaryArts;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MainCulinaryArts.MODID)
public class MainCulinaryArts {
    public static final String MODID = "culinary_arts";

    public MainCulinaryArts() {
        BlocksCulinaryArts.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsCulinaryArts.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainersCulinaryArts.register(FMLJavaModLoadingContext.get().getModEventBus());
        RecipeSerializersCulinaryArts.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntitiesCulinaryArts.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ItemsCulinaryArts.setup(event);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManagerCulinaryArts.doClientStuff(event);
        ItemColorsCulinaryArts.doClientStuff(event);
        ItemModelPropertiesCulinaryArts.doClientStuff(event);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }
    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onFinish(final LivingEntityUseItemEvent.Finish event) {
            ItemsCulinaryArts.onFinish(event);
        }
        @SubscribeEvent
        public static void onBiomeLoading(final BiomeLoadingEvent event) {
            FeaturesCulinaryArts.onBiomeLoading(event);
        }
    }
}