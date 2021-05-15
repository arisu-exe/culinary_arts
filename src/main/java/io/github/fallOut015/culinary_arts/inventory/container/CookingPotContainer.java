package io.github.fallOut015.culinary_arts.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class CookingPotContainer extends RecipeBookContainer<IInventory> {
    private IInventory container;
    private IIntArray currentBoilingTimes;
    private IIntArray targetBoilingTimes;
    protected World level;

    public CookingPotContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
    public CookingPotContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(9), new IntArray(9), new IntArray(9));
    }
    public CookingPotContainer(int id, PlayerInventory playerInventory, IInventory container, IIntArray currentBoilingTimes, IIntArray targetBoilingTimes) {
        this(ContainersCulinaryArts.COOKING_POT.get(), id);

        this.container = container;
        checkContainerSize(this.container, 9);
        this.currentBoilingTimes = currentBoilingTimes;
        checkContainerDataCount(this.currentBoilingTimes, 9);
        this.targetBoilingTimes = targetBoilingTimes;
        checkContainerDataCount(this.targetBoilingTimes, 9);
        this.level = playerInventory.player.level;

        for(int g = 0, g2 = 0; g < 3; ++ g) {
            for(int h = 0; h < 3; ++ h, ++ g2) {
                this.addSlot(new CookingPotSlot(this.container, g2, 62 + h * 18, 17 + g * 18));
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

        this.addDataSlots(currentBoilingTimes);
        this.addDataSlots(targetBoilingTimes);
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
        return 3;
    }
    @Override
    public int getGridHeight() {
        return 3;
    }
    @Override
    public int getSize() {
        return 9;
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
    public int getCurrentBoilingTime(int index) {
        return this.currentBoilingTimes.get(index);
    }
    public int getTargetBoilingTime(int index) {
        return this.targetBoilingTimes.get(index);
    }
    public List<Slot> getContainerSlots() {
        return this.slots.stream().filter(slot -> slot.container == this.container).collect(Collectors.toList());
    }
}