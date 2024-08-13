package com.prohitman.nethersquids.core.datagen;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.core.datagen.client.ModItemModelProvider;
import com.prohitman.nethersquids.core.datagen.client.ModLanguageProvider;
import com.prohitman.nethersquids.core.datagen.server.ModBlockTags;
import com.prohitman.nethersquids.core.datagen.server.ModPoiTypeTags;
import com.prohitman.nethersquids.core.datagen.server.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NetherSquids.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModItemModelProvider>)
                output -> new ModItemModelProvider(output, event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeClient(), (DataProvider.Factory<ModLanguageProvider>)
                output -> new ModLanguageProvider(dataGenerator.getPackOutput(), "en_us"));

        dataGenerator.addProvider(event.includeServer(), new ModRecipes(dataGenerator.getPackOutput(), lookupProvider));

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModPoiTypeTags>)
                output -> new ModPoiTypeTags(output, lookupProvider, event.getExistingFileHelper()));

        dataGenerator.addProvider(event.includeServer(), (DataProvider.Factory<ModBlockTags>)
                output -> new ModBlockTags(output, lookupProvider, event.getExistingFileHelper()));

        try {
            dataGenerator.run();
        } catch (IOException ignored) {
        }
    }
}
