package com.prohitman.nethersquids.core.events;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.entity.NetherSquid;
import com.prohitman.nethersquids.core.init.ModEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = NetherSquids.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NETHER_SQUID.get(), NetherSquid.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawns(@NotNull SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.NETHER_SQUID.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherSquid::checkNetherSquidSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }
}
