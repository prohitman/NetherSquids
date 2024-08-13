package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.entity.NetherSquid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NetherSquids.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<NetherSquid>> NETHER_SQUID =
            ENTITY_TYPES.register("nether_squid",
                    () -> EntityType.Builder.of(NetherSquid::new, MobCategory.WATER_AMBIENT)
                            .sized(0.8f, 0.8f).clientTrackingRange(10).build("nether_squid"));
}
