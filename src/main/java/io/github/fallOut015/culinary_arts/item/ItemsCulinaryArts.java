package io.github.fallOut015.culinary_arts.item;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import io.github.fallOut015.culinary_arts.block.BlocksCulinaryArts;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Method;
import java.util.Map;

public class ItemsCulinaryArts {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainCulinaryArts.MODID);



    // Building Blocks
    public static final RegistryObject<Item> SALTED_STONE = ITEMS.register("salted_stone", () -> new BlockItem(BlocksCulinaryArts.SALTED_STONE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SALT_BLOCK = ITEMS.register("salt_block", () -> new BlockItem(BlocksCulinaryArts.SALT_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    // Decorations
    public static final RegistryObject<Item> COOKING_POT = ITEMS.register("cooking_pot", () -> new BlockItem(BlocksCulinaryArts.COOKING_POT.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BRICK_OVEN = ITEMS.register("brick_oven", () -> new BlockItem(BlocksCulinaryArts.BRICK_OVEN.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));

    // Misc
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> MACARONAGE = ITEMS.register("macaronage", () -> new MultiColoredItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> WHIPPED_CREAM = ITEMS.register("whipped_cream", () -> new MultiColoredItem(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> BUTTERCREAM = ITEMS.register("buttercream", () -> new ColoredItem(16776913, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> GANACHE = ITEMS.register("ganache", () -> new ColoredItem(4657927, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> APPLE_JAM = ITEMS.register("apple_jam", () -> new ColoredItem(16094518, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> GRAPE_JAM = ITEMS.register("grape_jam", () -> new ColoredItem(14891953, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> MARINARA_SAUCE = ITEMS.register("marinara_sauce", () -> new ColoredItem(11872020, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> ALFREDO_SAUCE = ITEMS.register("alfredo_sauce", () -> new ColoredItem(16117961, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> CARBONARA_SAUCE = ITEMS.register("carbonara_sauce", () -> new ColoredItem(16646079, new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> PIZZA_DOUGH = ITEMS.register("pizza_dough", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> APPLE_CORE = ITEMS.register("apple_core", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> GOLDEN_APPLE_CORE = ITEMS.register("golden_apple_core", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> BEETROOT_STEMS = ITEMS.register("beetroot_stems", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> CARROT_STEM = ITEMS.register("carrot_stem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> GOLDEN_CARROT_STEM = ITEMS.register("golden_carrot_stem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> CHORUS_PIT = ITEMS.register("chorus_pit", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> MELON_RIND = ITEMS.register("melon_rind", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    // Food
    public static final RegistryObject<Item> SOGGY_FOOD = ITEMS.register("soggy_food", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.SOGGY_FOOD)));
    public static final RegistryObject<Item> BURNT_FOOD = ITEMS.register("burnt_food", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.BURNT_FOOD)));
    public static final RegistryObject<Item> HARD_BOILED_EGG = ITEMS.register("hard_boiled_egg", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.HARD_BOILED_EGG)));
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.CHEESE)));
    public static final RegistryObject<Item> MACARON_SHELL = ITEMS.register("macaron_shell", () -> new MultiColoredItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.MACARON_SHELL)));
    public static final RegistryObject<Item> MACARON = ITEMS.register("macaron", () -> new MacaronItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodsCulinaryArts.MACARON)));
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.BLUEBERRY).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.TOMATO).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> BANANA_BUNCH = ITEMS.register("banana_bunch", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.BANANA_BUNCH).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.CORN).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> SUGAR_COOKIE = ITEMS.register("sugar_cookie", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.SUGAR_COOKIE).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> APPLE_PIE = ITEMS.register("apple_pie", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.APPLE_PIE).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> TOAST = ITEMS.register("toast", () -> new Item(new Item.Properties().food(FoodsCulinaryArts.TOAST).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza", () -> new PizzaItem(new Item.Properties().food(FoodsCulinaryArts.PIZZA).tab(ItemGroup.TAB_FOOD)));

    /*
    Food Steamer

    Dough

    Egg Sandwich (hard_boiled_egg + bread)
    Shepherd's Pie
    Pizza
    Pasta
    Chocolate Cake
    Red Velvet Cake
    Carrot Cake
    Apple Cake
    Cheesecake
    Birthday Cake
    Wedding Cake
    Sponge Cake
    Thumbprint Cookie
    Blueberry Pie
    Chocolate Pie
     */



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }



    public static final Map<Item, ItemStack> FOOD_TO_SCRAPS = Maps.newHashMap();



    public static void setup(final FMLCommonSetupEvent event) {
        FOOD_TO_SCRAPS.put(Items.GOLDEN_APPLE, new ItemStack(GOLDEN_APPLE_CORE.get()));
        FOOD_TO_SCRAPS.put(Items.GOLDEN_CARROT, new ItemStack(GOLDEN_CARROT_STEM.get()));
        FOOD_TO_SCRAPS.put(Items.COOKED_BEEF, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_PORKCHOP, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_MUTTON, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_COD, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_SALMON, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_CHICKEN, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COOKED_RABBIT, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.MELON_SLICE, new ItemStack(MELON_RIND.get()));
        FOOD_TO_SCRAPS.put(Items.APPLE, new ItemStack(APPLE_CORE.get()));
        FOOD_TO_SCRAPS.put(Items.CARROT, new ItemStack(CARROT_STEM.get()));
        FOOD_TO_SCRAPS.put(Items.BEETROOT, new ItemStack(BEETROOT_STEMS.get()));
        FOOD_TO_SCRAPS.put(Items.SWEET_BERRIES, new ItemStack(Items.STICK));
        FOOD_TO_SCRAPS.put(Items.BEEF, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.PORKCHOP, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.MUTTON, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.CHICKEN, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.RABBIT, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.COD, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.SALMON, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.CHORUS_FRUIT, new ItemStack(CHORUS_PIT.get()));
        FOOD_TO_SCRAPS.put(Items.PUFFERFISH, new ItemStack(Items.BONE));
        FOOD_TO_SCRAPS.put(Items.TROPICAL_FISH, new ItemStack(Items.BONE));

        try {
            Method add = ComposterBlock.class.getDeclaredMethod("add", float.class, IItemProvider.class);
            add.setAccessible(true);
            add.invoke(null, 0.3f, MELON_RIND.get());
            add.invoke(null, 0.5f, APPLE_CORE.get());
            add.invoke(null, 0.5f, BEETROOT_STEMS.get());
            add.invoke(null, 0.5f, CARROT_STEM.get());
            add.setAccessible(false);
        } catch (Exception ignored) {
        }
    } // TODO recipes?

    public static void onFinish(final LivingEntityUseItemEvent.Finish event) {
        if(event.getItem().isEdible() && event.getEntityLiving() instanceof PlayerEntity && !((PlayerEntity) event.getEntityLiving()).isCreative()) {
            if(FOOD_TO_SCRAPS.containsKey(event.getItem().getItem())) {

                ItemStack result = FOOD_TO_SCRAPS.get(event.getItem().getItem());

                if(event.getEntityLiving().getItemInHand(event.getEntityLiving().getUsedItemHand()) == ItemStack.EMPTY && event.getEntityLiving() instanceof PlayerEntity && !(((PlayerEntity) event.getEntityLiving()).inventory.hasAnyOf(Sets.newHashSet(result.getItem())))) {
                    event.setResultStack(result);
                } else {
                    event.getEntityLiving().level.addFreshEntity(new ItemEntity(event.getEntityLiving().level, event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(), result));
                }

                // TODO
                // Make it so food scraps can be fed to animals.
                // Food recipes for each new scrap.
                // Put the scrap in your hand if it can. (look at cartomancy card code)
            }
        }
    }
}