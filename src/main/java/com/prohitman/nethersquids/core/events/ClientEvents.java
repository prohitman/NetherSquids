package com.prohitman.nethersquids.core.events;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.client.entity.NetherSquidRenderer;
import com.prohitman.nethersquids.client.screen.GrinderScreen;
import com.prohitman.nethersquids.core.init.ModEntities;
import com.prohitman.nethersquids.core.init.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = NetherSquids.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents  {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.NETHER_SQUID.get(), (pContext -> new NetherSquidRenderer(pContext, new SquidModel<>(pContext.bakeLayer(ModelLayers.GLOW_SQUID)))));
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event)
    {
        event.register(ModMenuTypes.GRINDER_MENU.get(), GrinderScreen::new);
    }
}
