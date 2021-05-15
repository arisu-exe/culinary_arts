package io.github.fallOut015.culinary_arts.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class BrickOvenContainer extends RecipeBookContainer<IInventory> {
    private IInventory container;
    private IIntArray currentBakingTimes;
    private IIntArray targetBakingTimes;
    protected World level;

    public BrickOvenContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
    public BrickOvenContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(4), new IntArray(4), new IntArray(4));
    }
    public BrickOvenContainer(int id, PlayerInventory playerInventory, IInventory container, IIntArray currentBakingTimes, IIntArray targetBakingTimes) {
        this(ContainersCulinaryArts.BRICK_OVEN.get(), id);

        this.container = container;
        checkContainerSize(this.container, 4);
        this.currentBakingTimes = currentBakingTimes;
        checkContainerDataCount(this.currentBakingTimes, 4);
        this.targetBakingTimes = targetBakingTimes;
        checkContainerDataCount(this.targetBakingTimes, 4);
        this.level = playerInventory.player.level;

        for(int g = 0, g2 = 0; g < 2; ++ g) {
            for(int h = 0; h < 2; ++ h, ++ g2) {
                this.addSlot(new BrickOvenSlot(this.container, g2, 71 + h * 18, 17 + g * 18));
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(currentBakingTimes);
        this.addDataSlots(targetBakingTimes);
    }

    @Override
    public void fillCraftSlotsStackedContents(RecipeItemHelper helper) {
        if (this.container instanceof IRecipeHelperPopulator) {
            ((IRecipeHelperPopulator)this.container).fillStackedContents(helper);
        }
    }
    @Override
    public void clearCraftingContent() {
        this.container.clearContent();
    }
    @Override
    public boolean recipeMatches(IRecipe<? super IInventory> recipe) {
        return recipe.matches(this.container, this.level);
    }
    @Override
    public int getResultSlotIndex() {
        return -1;
    }
    @Override
    public int getGridWidth() {
        return 2;
    }
    @Override
    public int getGridHeight() {
        return 2;
    }
    @Override
    public int getSize() {
        return 4;
    }
    @Override
    public RecipeBookCategory getRecipeBookType() {
        return RecipeBookCategory.FURNACE;
    }
    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return true;
        //return stillValid(IWorldPosCallable.NULL, playerEntity, BlocksCulinaryArts.COOKING_POT.get());
    }
    public int getCurrentBakingTime(int index) {
        return this.currentBakingTimes.get(index);
    }
    public int getTargetBakingTime(int index) {
        return this.targetBakingTimes.get(index);
    }
    public List<Slot> getContainerSlots() {
        return this.slots.stream().filter(slot -> slot.container == this.container).collect(Collectors.toList());
    }
}