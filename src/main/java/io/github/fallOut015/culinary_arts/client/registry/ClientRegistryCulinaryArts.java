package io.github.fallOut015.culinary_arts.client.registry;

import io.github.fallOut015.culinary_arts.client.renderer.tileentity.BrickOvenRenderer;
import io.github.fallOut015.culinary_arts.tileentity.TileEntitiesCulinaryArts;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientRegistryCulinaryArts {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntitiesCulinaryArts.BRICK_OVEN.get(), BrickOvenRenderer::new);
    }
}
