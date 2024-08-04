package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NetherSquids.MODID);

    public static RegistryObject<CreativeModeTab> NS_TAB = CREATIVE_MODE_TABS.register("ns_tab", () ->
            CreativeModeTab.builder().icon(() -> ModItems.TENTACLE.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.nethersquids"))
                    .displayItems((featureFlags, output) -> {
                        output.acceptAll(getTabItems());
                    }).build());

    private static List<ItemStack> getTabItems(){
        List<ItemStack> list = new LinkedList<>();

        list.add(ModBlocks.GRINDER.get().asItem().getDefaultInstance());

        list.addAll(ModItems.ITEMS.getEntries().stream().map(RegistryObject::get)
                .filter((item) -> (!(item instanceof BlockItem)
                        || item instanceof SignItem))
                .map(Item::getDefaultInstance).toList());

        return list;
    }
}
