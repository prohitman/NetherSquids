package com.prohitman.nethersquids.core.init;

import com.google.common.collect.ImmutableSet;
import com.prohitman.nethersquids.NetherSquids;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPois {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, NetherSquids.MODID);

    public static final RegistryObject<PoiType> SQUIDDING = POI_TYPES.register("squidding", () ->
            new PoiType(ImmutableSet.copyOf(ModBlocks.GRINDER.get().getStateDefinition().getPossibleStates()), 1, 1));
}
