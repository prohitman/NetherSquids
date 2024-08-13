package com.prohitman.nethersquids.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.prohitman.nethersquids.common.entity.LavaSquid;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaSquidRenderer<T extends LavaSquid> extends MobRenderer<T, SquidModel<T>> {
    private static final ResourceLocation SQUID_LOCATION = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/squid/squid.png");

    public LavaSquidRenderer(EntityRendererProvider.Context pContext, SquidModel<T> pModel) {
        super(pContext, pModel, 0.7F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(T pEntity) {
        return SQUID_LOCATION;
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pMatrixStack, float bob, float pRotationYaw, float pPartialTicks, float scale) {
        float f = Mth.lerp(pPartialTicks, pEntityLiving.xBodyRotO, pEntityLiving.xBodyRot);
        float f1 = Mth.lerp(pPartialTicks, pEntityLiving.zBodyRotO, pEntityLiving.zBodyRot);
        pMatrixStack.translate(0.0F, 0.5F, 0.0F);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - pRotationYaw));
        pMatrixStack.mulPose(Axis.XP.rotationDegrees(f));
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(f1));
        pMatrixStack.translate(0.0F, -1.2F, 0.0F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float getBob(T pLivingBase, float pPartialTicks) {
        return Mth.lerp(pPartialTicks, pLivingBase.oldTentacleAngle, pLivingBase.tentacleAngle);
    }
}
