package com.prohitman.nethersquids.core.datagen.server;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.core.init.ModPois;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModPoiTypeTags extends PoiTypeTagsProvider {
    public ModPoiTypeTags(PackOutput generator, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, pLookupProvider, NetherSquids.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(ModPois.SQUIDDING.getKey());
    }
}
