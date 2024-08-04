package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.entity.NetherSquid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NetherSquids.MODID);
    public static final RegistryObject<EntityType<NetherSquid>> NETHER_SQUID =
            ENTITY_TYPES.register("nether_squid",
                    () -> EntityType.Builder.of(NetherSquid::new, MobCategory.WATER_AMBIENT)
                            .sized(0.8f, 0.8f).clientTrackingRange(10).build("nether_squid"));
}
