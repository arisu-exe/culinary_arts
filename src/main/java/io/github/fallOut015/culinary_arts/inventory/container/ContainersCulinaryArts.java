package io.github.fallOut015.culinary_arts.inventory.container;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersCulinaryArts {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MainCulinaryArts.MODID);



    public static final RegistryObject<ContainerType<CookingPotContainer>> COOKING_POT = CONTAINERS.register("cooking_pot", () -> new ContainerType<>(CookingPotContainer::new));
    public static final RegistryObject<ContainerType<BrickOvenContainer>> BRICK_OVEN = CONTAINERS.register("brick_oven", () -> new ContainerType<>(BrickOvenContainer::new));



    public static void register(IEventBus bus) {
        CONTAINERS.register(bus);
    }
}
