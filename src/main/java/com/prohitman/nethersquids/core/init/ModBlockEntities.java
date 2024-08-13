package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.blocks.entity.GrinderBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NetherSquids.MODID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrinderBlockEntity>> GRINDER_BE =
            BLOCK_ENTITIES.register("grinder_be", () ->
                    BlockEntityType.Builder.of(GrinderBlockEntity::new,
                            ModBlocks.GRINDER.get()).build(null));
}
