package com.prohitman.nethersquids.core.events;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.client.entity.NetherSquidRenderer;
import com.prohitman.nethersquids.client.screen.GrinderScreen;
import com.prohitman.nethersquids.core.init.ModEntities;
import com.prohitman.nethersquids.core.init.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NetherSquids.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents  {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NETHER_SQUID.get(), (pContext -> new NetherSquidRenderer(pContext, new SquidModel<>(pContext.bakeLayer(ModelLayers.GLOW_SQUID)))));
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(ModMenuTypes.GRINDER_MENU.get(), GrinderScreen::new);
    }
}
