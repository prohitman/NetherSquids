package com.prohitman.nethersquids.client.entity;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.entity.NetherSquid;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NetherSquidRenderer extends LavaSquidRenderer<NetherSquid> {
    private static final ResourceLocation NETHER_SQUID_LOCATION = new ResourceLocation(NetherSquids.MODID,"textures/entity/nether_squid.png");

    public NetherSquidRenderer(EntityRendererProvider.Context p_174136_, SquidModel<NetherSquid> p_174137_) {
        super(p_174136_, p_174137_);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(NetherSquid p_174144_) {
        return NETHER_SQUID_LOCATION;
    }

    protected int getBlockLightLevel(NetherSquid p_174146_, BlockPos p_174147_) {
        int i = (int) Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float)p_174146_.getDarkTicksRemaining() / 10.0F);
        return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(p_174146_, p_174147_));
    }
}
