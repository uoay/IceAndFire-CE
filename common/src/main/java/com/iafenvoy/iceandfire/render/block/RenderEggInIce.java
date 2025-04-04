package com.iafenvoy.iceandfire.render.block;

import com.iafenvoy.iceandfire.entity.block.BlockEntityEggInIce;
import com.iafenvoy.iceandfire.render.model.ModelDragonEgg;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class RenderEggInIce<T extends BlockEntityEggInIce> implements BlockEntityRenderer<T> {
    public RenderEggInIce(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(T egg, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ModelDragonEgg model = new ModelDragonEgg();
        if (egg.type != null) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5, -0.8F, 0.5F);
            matrixStackIn.push();
            model.renderFrozen(egg);
            model.render(matrixStackIn, bufferIn.getBuffer(RenderPodium.getEggTexture(egg.type)), combinedLightIn, combinedOverlayIn, -1);
            matrixStackIn.pop();
            matrixStackIn.pop();
        }
    }
}
