package io.github.fallOut015.culinary_arts.client;

import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderTypeLookupCulinaryArts {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlocksCulinaryArts.TOMATO.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlocksCulinaryArts.TOMATO_PLANT.get(), RenderType.cutout());
    }
}
