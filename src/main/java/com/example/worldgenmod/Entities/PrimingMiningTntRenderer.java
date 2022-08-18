package com.example.worldgenmod.Entities;

import com.example.worldgenmod.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PrimingMiningTntRenderer extends EntityRenderer<PrimedMiningTnt> {

    protected PrimingMiningTntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(PrimedMiningTnt tnt, float entityYaw, float partialTick, PoseStack matrix, MultiBufferSource renderer, int light) {
        matrix.pushPose();
        matrix.translate(0, 0.5, 0);
        if (tnt.getFuse() - partialTick + 1.0F < 10.0F) {
            float f = 1.0F - (tnt.getFuse() - partialTick + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            matrix.scale(f1, f1, f1);
        }
        matrix.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrix.translate(-0.5, -0.5, 0.5);
        matrix.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(Registration.MINING_EXPLOSIVES.get().defaultBlockState(), matrix, renderer, light,
                tnt.getFuse() / 5 % 2 == 0);
        matrix.popPose();
        super.render(tnt, entityYaw, partialTick, matrix, renderer, light);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(@NotNull PrimedMiningTnt entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
