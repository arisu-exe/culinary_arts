package io.github.fallOut015.culinary_arts.tileentity;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntitiesCulinaryArts {
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MainCulinaryArts.MODID);



    public static final RegistryObject<TileEntityType<CookingPotTileEntity>> COOKING_POT = TILE_ENTITIES.register("cooking_pot", () -> TileEntityType.Builder.of(CookingPotTileEntity::new, BlocksCulinaryArts.COOKING_POT.get()).build(Util.fetchChoiceType(TypeReferences.BLOCK_ENTITY, "cooking_pot")));
    public static final RegistryObject<TileEntityType<BrickOvenTileEntity>> BRICK_OVEN = TILE_ENTITIES.register("brick_oven", () -> TileEntityType.Builder.of(BrickOvenTileEntity::new, BlocksCulinaryArts.BRICK_OVEN.get()).build(Util.fetchChoiceType(TypeReferences.BLOCK_ENTITY, "brick_oven")));



    public static void register(IEventBus bus) {
        TILE_ENTITIES.register(bus);
    }
}