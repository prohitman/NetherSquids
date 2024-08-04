package com.prohitman.nethersquids.client.armor;

import com.prohitman.nethersquids.common.items.FireBootsItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FireBootsRenderer extends GeoArmorRenderer<FireBootsItem>{
    public FireBootsRenderer() {
        super(new FireBootsModel());
    }
}
