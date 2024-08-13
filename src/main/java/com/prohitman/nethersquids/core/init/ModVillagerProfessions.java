package com.prohitman.nethersquids.core.init;

import com.google.common.collect.ImmutableSet;
import com.prohitman.nethersquids.NetherSquids;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagerProfessions {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, NetherSquids.MODID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> SQUIDDER = VILLAGER_PROFESSIONS.register("squidder", () ->
            new VillagerProfession("squidder",
                    (poiTypeHolder) -> poiTypeHolder.is(ModPois.SQUIDDING.getKey()),
                    (poiTypeHolder) -> poiTypeHolder.is(ModPois.SQUIDDING.getKey()),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_WEAPONSMITH
                    ));

}
