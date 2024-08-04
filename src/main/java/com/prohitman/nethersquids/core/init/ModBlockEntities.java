package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.blocks.entity.GrinderBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NetherSquids.MODID);

    public static final RegistryObject<BlockEntityType<GrinderBlockEntity>> GRINDER_BE =
            BLOCK_ENTITIES.register("grinder_be", () ->
                    BlockEntityType.Builder.of(GrinderBlockEntity::new,
                            ModBlocks.GRINDER.get()).build(null));
}
