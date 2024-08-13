package com.prohitman.nethersquids.core.events;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.entity.NetherSquid;
import com.prohitman.nethersquids.core.init.ModEntities;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = NetherSquids.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NETHER_SQUID.get(), NetherSquid.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawns(@NotNull RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.NETHER_SQUID.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherSquid::checkNetherSquidSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }
}
