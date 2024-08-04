package com.prohitman.nethersquids;

import com.mojang.logging.LogUtils;
import com.prohitman.nethersquids.core.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NetherSquids.MODID)
public class NetherSquids
{
    public static final String MODID = "nethersquids";

    public NetherSquids()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModVillagerProfessions.VILLAGER_PROFESSIONS.register(modEventBus);
        ModPois.POI_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
    }
}
