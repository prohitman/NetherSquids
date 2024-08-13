package com.prohitman.nethersquids.core.events;

import com.google.common.base.Objects;
import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.core.init.ModBlocks;
import com.prohitman.nethersquids.core.init.ModItems;
import com.prohitman.nethersquids.core.init.ModVillagerProfessions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = NetherSquids.MODID, bus = EventBusSubscriber.Bus.GAME)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void generateObsidian(EntityTickEvent.Pre event){
        if(!event.getEntity().level().isClientSide){
            BlockPos currentblockpos = event.getEntity().blockPosition();
            if(event.getEntity() instanceof LivingEntity livingEntity){
                //if (!Objects.equal(livingEntity.lastPos, currentblockpos)) {
                    ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
                    if (itemStack.is(ModItems.FIRE_BOOTS.get()) && livingEntity.onGround()) {
                        BlockState blockstate = ModBlocks.FIRED_OBSIDIAN.get().defaultBlockState();
                        int i = Math.min(16, 4);
                        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                        for (BlockPos blockpos : BlockPos.betweenClosed(livingEntity.blockPosition().offset(-i, -1, -i), event.getEntity().blockPosition().offset(i, -1, i))) {//might need ATs with lastpos
                            if (blockpos.closerToCenterThan(livingEntity.position(), (double) i)) {
                                blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                                BlockState blockstate1 = livingEntity.level().getBlockState(blockpos$mutableblockpos);
                                if (blockstate1.isAir()) {
                                    BlockState blockstate2 = livingEntity.level().getBlockState(blockpos);
                                    if (blockstate2 == Blocks.LAVA.defaultBlockState() && blockstate.canSurvive(event.getEntity().level(), blockpos) && event.getEntity().level().isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !EventHooks.onBlockPlace(event.getEntity(), BlockSnapshot.create(event.getEntity().level().dimension(), event.getEntity().level(), blockpos), Direction.UP)) {
                                        livingEntity.level().setBlockAndUpdate(blockpos, blockstate);
                                        livingEntity.level().scheduleTick(blockpos, ModBlocks.FIRED_OBSIDIAN.get(), Mth.nextInt(event.getEntity().getRandom(), 60, 120));
                                    }
                                }
                            }
                        }
                    }
                //}
            }
        }
    }

    @SubscribeEvent
    public static void addSquidderTrades(VillagerTradesEvent event){
        if(event.getType() == ModVillagerProfessions.SQUIDDER.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.TENTACLE.get(), 3),
                    new ItemStack(Items.EMERALD, 1),
                    8, 8, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.TENTACLE.get(), 1),
                    8, 8, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(Blocks.MAGMA_BLOCK, 1),
                    15, 6, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Blocks.MAGMA_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 1),
                    15, 6, 0.5f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(Items.LAVA_BUCKET, 1),
                    3, 10, 0.2f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(ModItems.SAW.get(), 1),
                    5, 15, 0.12f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 40),
                    new ItemStack(ModItems.BEAK.get(), 1),
                    3, 25, 0.2f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.BEAK.get(), 1),
                    new ItemStack(Items.EMERALD, 30),
                    3, 25, 0.2f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.FIRE_BOOTS.get(), 1),
                    new ItemStack(Items.EMERALD, 64),
                    1, 40, 0.25f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 64),
                    new ItemStack(ModItems.FIRE_BOOTS.get(), 1),
                    1, 40, 0.25f));

        }
    }
}
