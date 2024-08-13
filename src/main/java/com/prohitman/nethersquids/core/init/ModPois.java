package com.prohitman.nethersquids.core.init;

import com.google.common.collect.ImmutableSet;
import com.prohitman.nethersquids.NetherSquids;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPois {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, NetherSquids.MODID);

    public static final DeferredHolder<PoiType, PoiType> SQUIDDING = POI_TYPES.register("squidding", () ->
            new PoiType(ImmutableSet.copyOf(ModBlocks.GRINDER.get().getStateDefinition().getPossibleStates()), 1, 1));
}
