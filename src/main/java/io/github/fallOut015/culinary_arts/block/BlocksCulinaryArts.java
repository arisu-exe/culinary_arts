package io.github.fallOut015.culinary_arts.block;

import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class BlocksCulinaryArts {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MainCulinaryArts.MODID);



    public static final RegistryObject<Block> SALTED_STONE = BLOCKS.register("salted_stone", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(0)) {
        @Override
        protected int xpOnDrop(Random p_220281_1_) {
            return MathHelper.nextInt(p_220281_1_, 0, 2);
        }
    });
    public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register("salt_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SNOW).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(0)));

    public static final RegistryObject<Block> COOKING_POT = BLOCKS.register("cooking_pot", () -> new CookingPotBlock(AbstractBlock.Properties.of(Material.METAL).noOcclusion()));
    public static final RegistryObject<Block> BRICK_OVEN = BLOCKS.register("brick_oven", () -> new BrickOvenBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion()));
    // Food Steamer
    public static final RegistryObject<Block> TOMATO = BLOCKS.register("tomato", () -> new TomatoTopBlock(Block.Properties.of(Material.PLANT, MaterialColor.PLANT).strength(1.0f, 0.5f).noOcclusion().noCollission().randomTicks()));
    public static final RegistryObject<Block> TOMATO_PLANT = BLOCKS.register("tomato_plant", () -> new TomatoBlock(Block.Properties.of(Material.PLANT, MaterialColor.PLANT).strength(1.0f, 0.5f).noOcclusion().noCollission().randomTicks()));



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}