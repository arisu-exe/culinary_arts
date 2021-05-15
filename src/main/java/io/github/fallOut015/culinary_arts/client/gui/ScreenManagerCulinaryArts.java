package io.github.fallOut015.culinary_arts.client.gui;

import io.github.fallOut015.culinary_arts.client.gui.screen.inventory.BrickOvenScreen;
import io.github.fallOut015.culinary_arts.client.gui.screen.inventory.CookingPotScreen;
import io.github.fallOut015.culinary_arts.inventory.container.ContainersCulinaryArts;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ScreenManagerCulinaryArts {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.register(ContainersCulinaryArts.COOKING_POT.get(), CookingPotScreen::new);
        ScreenManager.register(ContainersCulinaryArts.BRICK_OVEN.get(), BrickOvenScreen::new);
    }
}