package com.prohitman.nethersquids.core.init;

import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.common.items.FireBootsItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NetherSquids.MODID);

    public static final DeferredItem<Item> BEAK = ITEMS.register("beak", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> SAW = ITEMS.register("saw", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TENTACLE = ITEMS.register("tentacle", () -> new Item(new Item.Properties().fireResistant().food(ModFoods.TENTACLE)));
    public static final DeferredItem<Item> DEAD_SQUID = ITEMS.register("dead_squid", () -> new Item(new Item.Properties().fireResistant().stacksTo(16)));
    public static final DeferredItem<Item> FIRE_BOOTS = ITEMS.register("fire_boots", () -> new FireBootsItem(ArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final DeferredItem<Item> NETHER_SQUID_SPAWN_EGG = ITEMS.register("nether_squid_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.NETHER_SQUID, 0x9F3A3A, 0xFF9191, new Item.Properties()));

}
