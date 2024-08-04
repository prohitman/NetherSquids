package com.prohitman.nethersquids.core.init;

import com.google.common.collect.ImmutableSet;
import com.prohitman.nethersquids.NetherSquids;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagerProfessions {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, NetherSquids.MODID);

    public static final RegistryObject<VillagerProfession> SQUIDDER = VILLAGER_PROFESSIONS.register("squidder", () ->
            new VillagerProfession("squidder",
                    (poiTypeHolder) -> poiTypeHolder.is(ModPois.SQUIDDING.getKey()),
                    (poiTypeHolder) -> poiTypeHolder.is(ModPois.SQUIDDING.getKey()),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_WEAPONSMITH
                    ));

}
