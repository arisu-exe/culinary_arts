package io.github.fallOut015.culinary_arts.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.fallOut015.culinary_arts.MainCulinaryArts;
import io.github.fallOut015.culinary_arts.inventory.container.CookingPotContainer;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class CookingPotScreen extends ContainerScreen<CookingPotContainer> implements IRecipeShownListener {
    private static final ResourceLocation COOKING_POT_GUI_TEXTURE = new ResourceLocation(MainCulinaryArts.MODID, "textures/gui/container/cooking_pot.png");
    public final AbstractRecipeBookGui recipeBookComponent;

    public CookingPotScreen(CookingPotContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);
        this.recipeBookComponent = new FurnaceRecipeGui(); // TODO
    }

    @Override
    public void render(MatrixStack stack, int x, int y, float p_230430_4_) {
        this.renderBackground(stack);
        /*if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(stack, p_230430_4_, x, y);
            this.recipeBookComponent.render(stack, x, y, p_230430_4_);
        } else {*/
            //this.recipeBookComponent.render(stack, x, y, p_230430_4_);
            super.render(stack, x, y, p_230430_4_);
            //this.recipeBookComponent.renderGhostRecipe(stack, this.leftPos, this.topPos, true, p_230430_4_);
        //}

        for(Slot slot : this.getMenu().getContainerSlots()) {
            if(slot.hasItem()) {
                RenderSystem.disableDepthTest();
                RenderSystem.disableTexture();
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuilder();
                float i = ((float) this.menu.getCurrentBoilingTime(slot.index)) / (2f * (float) this.menu.getTargetBoilingTime(slot.index));
                int c = i >= 1.0 ? 16711680 : i >= 0.5 ? 65280 : 16777215;
                this.fillRect(bufferbuilder, slot.x + 2 + this.leftPos, slot.y + 13 + this.topPos, 13, 2, 0, 0, 0, 255);
                this.fillRect(bufferbuilder, slot.x + 2 + this.leftPos, slot.y + 13 + this.topPos, Math.round(i * 13f), 1, c >> 16 & 255, c >> 8 & 255, c & 255, 255);
                RenderSystem.enableBlend();
                RenderSystem.enableAlphaTest();
                RenderSystem.enableTexture();
                RenderSystem.enableDepthTest();

                // TODO render tick in middle for perfect boiling
            }
        }

        this.renderTooltip(stack, x, y);
        //this.recipeBookComponent.renderTooltip(stack, this.leftPos, this.topPos, x, y);
    }
    @Override
    protected void renderBg(MatrixStack stack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(stack);
        this.minecraft.getTextureManager().bind(COOKING_POT_GUI_TEXTURE);
        this.blit(stack, this.leftPos, this.topPos, 0, 0, this.width, this.height);
    }
    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }
    @Override
    public RecipeBookGui getRecipeBookComponent() {
        return this.recipeBookComponent;
    }

    private void fillRect(BufferBuilder buffer, int x, int y, int w, int h, int r, int g, int b, int a) {
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex((double)(x + 0), (double)(y + 0), 0.0D).color(r, g, b, a).endVertex();
        buffer.vertex((double)(x + 0), (double)(y + h), 0.0D).color(r, g, b, a).endVertex();
        buffer.vertex((double)(x + w), (double)(y + h), 0.0D).color(r, g, b, a).endVertex();
        buffer.vertex((double)(x + w), (double)(y + 0), 0.0D).color(r, g, b, a).endVertex();
        Tessellator.getInstance().end();
    }
}