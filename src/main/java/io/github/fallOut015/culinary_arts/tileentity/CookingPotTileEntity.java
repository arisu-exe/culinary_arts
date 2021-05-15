package io.github.fallOut015.culinary_arts.tileentity;

import io.github.fallOut015.culinary_arts.inventory.container.CookingPotContainer;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.item.crafting.CookingPotRecipe;
import io.github.fallOut015.culinary_arts.item.crafting.RecipesTypes;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class CookingPotTileEntity extends LockableTileEntity implements IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    protected ITextComponent defaultName = new TranslationTextComponent("container.cooking_pot");
    protected NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);
    private final IIntArray currentBoilingTimes = new IntArray(9);
    private final IIntArray targetBoilingTimes = new IntArray(9);
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public CookingPotTileEntity() {
        this(TileEntitiesCulinaryArts.COOKING_POT.get());
    }
    public CookingPotTileEntity(TileEntityType<?> type) {
        super(type);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return defaultName;
    }
    @Override
    protected Container createMenu(int id, PlayerInventory inventory) {
        return new CookingPotContainer(id, inventory, this, this.currentBoilingTimes, this.targetBoilingTimes);
    }
    @Override
    public int getContainerSize() {
        return this.items.size();
    }
    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }
    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }
    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.items, index, count);
    }
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }
    @Override
    public void setItem(int index, ItemStack stack) {
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        CookingPotRecipe recipe = this.level.getRecipeManager().getRecipeFor(RecipesTypes.COOKING_POT, new Inventory(this.items.get(index)), this.level).orElse(null);
        if(recipe != null) {
            this.currentBoilingTimes.set(index, 0);
            this.targetBoilingTimes.set(index, recipe.getCookingTime());
        }

        this.setChanged();
    }
    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return true;

        /*if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return p_70300_1_.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }*/
    }
    @Override
    public void clearContent() {
        this.items.clear();
    }
    @Override
    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }
    }
    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> p_193056_1_) {
        if (p_193056_1_ != null) {
            ResourceLocation resourcelocation = p_193056_1_.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }
    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed() {
        return null;
    }
    // Can place item?
    @Override
    public void tick() {
        // if not clientSide

        for(int i = 0; i < this.items.size(); ++ i) {
            if(!this.items.get(i).isEmpty() && this.currentBoilingTimes.get(i) < this.targetBoilingTimes.get(i) * 2) {
                this.currentBoilingTimes.set(i, this.currentBoilingTimes.get(i) + 1);
            }
            if(this.items.get(i).isEmpty() && this.currentBoilingTimes.get(i) != 0) {
                this.currentBoilingTimes.set(i, 0);
            }

            if(!this.getItem(i).isEmpty()) {
                if(this.currentBoilingTimes.get(i) >= this.targetBoilingTimes.get(i) * 2 && !this.items.get(i).sameItem(new ItemStack(ItemsCulinaryArts.SOGGY_FOOD.get()))) {
                    this.setItem(i, new ItemStack(ItemsCulinaryArts.SOGGY_FOOD.get()));
                } else if(this.currentBoilingTimes.get(i) >= this.targetBoilingTimes.get(i)) {
                    final int finalI = i;
                    this.level.getRecipeManager().getRecipeFor(RecipesTypes.COOKING_POT, new Inventory(this.items.get(i)), this.level).ifPresent(recipe -> {
                        this.setItem(finalI, recipe.getResultItem());
                    });
                }
            }

            this.setChanged();
        }
    }
    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);

        ItemStackHelper.saveAllItems(tag, this.items);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((o, i) -> {
            compoundnbt.putInt(o.toString(), i);
        });
        tag.put("RecipesUsed", compoundnbt);
        int[] currentTimes = new int[9];
        for(int i = 0; i < this.currentBoilingTimes.getCount(); ++ i) {
            currentTimes[i] = this.currentBoilingTimes.get(i);
        }
        tag.putIntArray("currentBoilingTimes", currentTimes);
        int[] targetTimes = new int[9];
        for(int i = 0; i < this.targetBoilingTimes.getCount(); ++ i) {
            targetTimes[i] = this.targetBoilingTimes.get(i);
        }
        tag.putIntArray("targetBoilingTimes", targetTimes);

        return tag;
    }
    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.items);
        if(tag.contains("currentBoilingTimes")) {
            int[] times = tag.getIntArray("currentBoilingTimes");
            for(int i = 0; i < times.length; ++ i) {
                this.currentBoilingTimes.set(i, times[i]);
            }
        }
        if(tag.contains("targetBoilingTimes")) {
            int[] times = tag.getIntArray("targetBoilingTimes");
            for(int i = 0; i < times.length; ++ i) {
                this.targetBoilingTimes.set(i, times[i]);
            }
        }
        CompoundNBT compoundnbt = tag.getCompound("RecipesUsed");
        for(String s : compoundnbt.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }
    }

    public boolean mayPlace(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor(RecipesTypes.COOKING_POT, new Inventory(stack), this.level).isPresent();
    }
}