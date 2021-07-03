package io.github.fallOut015.culinary_arts.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.fallOut015.culinary_arts.tileentity.BrickOvenTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class BrickOvenRenderer extends TileEntityRenderer<BrickOvenTileEntity>  {
    public BrickOvenRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(BrickOvenTileEntity te, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, int packedLightIn, int packedOverlayIn) {
        // TODO render each baking item
        // TODO also, not in this class, spawn particles around item when it finished cooking
        for(int i = 0; i < te.getContainerSize(); ++ i) {
            ItemStack itemStack = te.getItem(i);
            if(!itemStack.isEmpty()) {
                stack.pushPose();
                stack.scale(0.75f, 0.75f, 0.75f);
                stack.translate(0, 0.5f, 0);
                stack.mulPose(new Quaternion(Vector3f.XP, 90, false));
                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemCameraTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, stack, renderTypeBuffer);
                stack.popPose();
            }
        }
    }
}
