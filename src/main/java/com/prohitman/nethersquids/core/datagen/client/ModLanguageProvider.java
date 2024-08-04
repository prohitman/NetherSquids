package com.prohitman.nethersquids.core.datagen.client;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.core.init.ModBlocks;
import com.prohitman.nethersquids.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, NetherSquids.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(ModItems.BEAK);
        addItem(ModItems.SAW);
        addItem(ModItems.TENTACLE);
        addItem(ModItems.FIRE_BOOTS);
        add( "item.nethersquids.dead_squid", "Nether Squid Corpse");

        addItem(ModItems.NETHER_SQUID_SPAWN_EGG);

        addBlock(ModBlocks.GRINDER);

        add( "entity.nethersquids.nether_squid", "Nether Squid");
        add( "entity.minecraft.villager.nethersquids.squidder", "Squidder");

        add("itemGroup.nethersquids", "Nether Squids Mod");
    }

    public void addBlock(RegistryObject<Block> key) {
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")));
    }

    public void addItem(RegistryObject<Item> key){
        add(key.get().getDescriptionId(), StringUtils.capitaliseAllWords(key.getId().getPath().replaceAll("_", " ")));
    }
}
