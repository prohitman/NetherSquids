package com.prohitman.nethersquids.client.armor;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.items.FireBootsItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FireBootsModel extends GeoModel<FireBootsItem> {

    @Override
    public ResourceLocation getModelResource(FireBootsItem fireBootsItem) {
        return new ResourceLocation(NetherSquids.MODID, "geo/fireboots.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FireBootsItem fireBootsItem) {
        return new ResourceLocation(NetherSquids.MODID, "textures/armor/fireboots.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FireBootsItem fireBootsItem) {
        return new ResourceLocation(NetherSquids.MODID, "animations/fireboots.animation.json");
    }
}
