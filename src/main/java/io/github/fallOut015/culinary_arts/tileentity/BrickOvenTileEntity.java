package io.github.fallOut015.culinary_arts.tileentity;

import io.github.fallOut015.culinary_arts.inventory.container.BrickOvenContainer;
import io.github.fallOut015.culinary_arts.inventory.container.CookingPotContainer;
import io.github.fallOut015.culinary_arts.item.ItemsCulinaryArts;
import io.github.fallOut015.culinary_arts.item.crafting.CookingPotRecipe;
import io.github.fallOut015.culinary_arts.item.crafting.RecipesTypes;
import io.github.fallOut015.culinary_arts.item.crafting.SpecialBakingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
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

public class BrickOvenTileEntity extends LockableTileEntity implements IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
    protected ITextComponent defaultName = new TranslationTextComponent("container.brick_oven");
    protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private final IIntArray currentBakingTimes = new IntArray(4);
    private final IIntArray targetBakingTimes = new IntArray(4);
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public BrickOvenTileEntity() {
        this(TileEntitiesCulinaryArts.BRICK_OVEN.get());
    }
    public BrickOvenTileEntity(TileEntityType<?> type) {
        super(type);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return defaultName;
    }
    @Override
    protected Container createMenu(int id, PlayerInventory inventory) {
        return new BrickOvenContainer(id, inventory, this, this.currentBakingTimes, this.targetBakingTimes);
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
        SpecialBakingRecipe recipe = this.level.getRecipeManager().getRecipeFor(RecipesTypes.BRICK_OVEN, new Inventory(this.items.get(index)), this.level).orElse(null);
        if(recipe != null) {
            this.currentBakingTimes.set(index, 0);
            this.targetBakingTimes.set(index, recipe.getBakingTime());
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
            if(!this.items.get(i).isEmpty() && this.currentBakingTimes.get(i) < this.targetBakingTimes.get(i) * 2) {
                this.currentBakingTimes.set(i, this.currentBakingTimes.get(i) + 1);
            }
            if(this.items.get(i).isEmpty() && this.currentBakingTimes.get(i) != 0) {
                this.currentBakingTimes.set(i, 0);
            }

            if(!this.getItem(i).isEmpty()) {
                if(this.currentBakingTimes.get(i) >= this.targetBakingTimes.get(i) * 2 && !this.items.get(i).sameItem(new ItemStack(ItemsCulinaryArts.BURNT_FOOD.get()))) {
                    this.setItem(i, new ItemStack(ItemsCulinaryArts.BURNT_FOOD.get()));
                } else if(this.currentBakingTimes.get(i) >= this.targetBakingTimes.get(i)) {
                    final int finalI = i;
                    this.level.getRecipeManager().getRecipeFor(RecipesTypes.BRICK_OVEN, new Inventory(this.items.get(i)), this.level).ifPresent(recipe -> {
                        recipe.setResultItem(new Inventory(this.items.get(finalI)));
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
        int[] currentTimes = new int[this.getContainerSize()];
        for(int i = 0; i < this.currentBakingTimes.getCount(); ++ i) {
            currentTimes[i] = this.currentBakingTimes.get(i);
        }
        tag.putIntArray("currentBakingTimes", currentTimes);
        int[] targetTimes = new int[this.getContainerSize()];
        for(int i = 0; i < this.targetBakingTimes.getCount(); ++ i) {
            targetTimes[i] = this.targetBakingTimes.get(i);
        }
        tag.putIntArray("targetBakingTimes", targetTimes);

        return tag;
    }
    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.items);
        if(tag.contains("currentBakingTimes")) {
            int[] times = tag.getIntArray("currentBakingTimes");
            for(int i = 0; i < times.length; ++ i) {
                this.currentBakingTimes.set(i, times[i]);
            }
        }
        if(tag.contains("targetBakingTimes")) {
            int[] times = tag.getIntArray("targetBakingTimes");
            for(int i = 0; i < times.length; ++ i) {
                this.targetBakingTimes.set(i, times[i]);
            }
        }
        CompoundNBT compoundnbt = tag.getCompound("RecipesUsed");
        for(String s : compoundnbt.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }
    }

    public boolean mayPlace(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor(RecipesTypes.BRICK_OVEN, new Inventory(stack), this.level).isPresent();
    }
}