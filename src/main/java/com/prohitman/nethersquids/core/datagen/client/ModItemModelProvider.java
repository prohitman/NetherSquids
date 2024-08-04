package com.prohitman.nethersquids.core.datagen.client;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.core.init.ModBlocks;
import com.prohitman.nethersquids.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NetherSquids.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Items

        createSingle(ModItems.SAW);
        createSingle(ModItems.BEAK);
        createSingle(ModItems.TENTACLE);
        withExistingParent(ModItems.NETHER_SQUID_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        createParent(ModBlocks.GRINDER);

        //Armors
        createSingle(ModItems.FIRE_BOOTS);
    }

    private void createBlockSingle(RegistryObject<Block> block, String location){
        singleTexture((block.getId().getPath()),
                mcLoc("item/generated"),
                "layer0", modLoc(location));
    }

    private void createBlockSingleHandheld(RegistryObject<Block> block, String location){
        singleTexture((block.getId().getPath()),
                mcLoc("item/handheld"),
                "layer0", modLoc(location));
    }

    private void createParent(RegistryObject<Block> handler) {
        withExistingParent(handler.getId().getPath(), modLoc( "block/" + handler.getId().getPath()));
    }

    private void createParentBlock(RegistryObject<Block> handler) {
        withExistingParent(handler.getId().getPath(), modLoc( "block/" + handler.getId().getPath()));
    }

    private String name(RegistryObject<Block> block) {
        return block.getId().getPath();
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void createSingle(RegistryObject<Item> item) {
        ModelFile generated = getExistingFile(mcLoc("item/generated"));
        getBuilder(item.getId().getPath()).parent(generated).texture("layer0", modLoc( "item/" + item.getId().getPath()));
    }


}
