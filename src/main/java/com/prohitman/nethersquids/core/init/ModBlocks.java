package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.blocks.FiredObsidianBlock;
import com.prohitman.nethersquids.common.blocks.GrinderBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NetherSquids.MODID);

    public static final DeferredBlock<Block> FIRED_OBSIDIAN = BLOCKS.register("fired_obsidian", () -> new FiredObsidianBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
    public static final DeferredBlock<Block> GRINDER = createRegistry("grinder", () -> new GrinderBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.WOOD).requiresCorrectToolForDrops().strength(2F).noOcclusion()), new Item.Properties());

    public static <T extends Block> DeferredBlock<Block> createRegistry(String name, Supplier<T> block, Item.Properties properties) {
        DeferredBlock<Block> object = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(object.get(), properties));

        return object;
    }
}
