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
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = NetherSquids.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void generateObsidian(LivingEvent.LivingTickEvent event){
        if(!event.getEntity().level().isClientSide){
            BlockPos currentblockpos = event.getEntity().blockPosition();
            if (!Objects.equal(event.getEntity().lastPos, currentblockpos)) {
                ItemStack itemStack = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
                if (itemStack.is(ModItems.FIRE_BOOTS.get()) && event.getEntity().onGround()) {
                    BlockState blockstate = ModBlocks.FIRED_OBSIDIAN.get().defaultBlockState();
                    int i = Math.min(16, 4);
                    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                    for (BlockPos blockpos : BlockPos.betweenClosed(event.getEntity().blockPosition().offset(-i, -1, -i), event.getEntity().blockPosition().offset(i, -1, i))) {//might need ATs with lastpos
                        if (blockpos.closerToCenterThan(event.getEntity().position(), (double) i)) {
                            blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                            BlockState blockstate1 = event.getEntity().level().getBlockState(blockpos$mutableblockpos);
                            if (blockstate1.isAir()) {
                                BlockState blockstate2 = event.getEntity().level().getBlockState(blockpos);
                                if (blockstate2 == Blocks.LAVA.defaultBlockState() && blockstate.canSurvive(event.getEntity().level(), blockpos) && event.getEntity().level().isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(event.getEntity(), BlockSnapshot.create(event.getEntity().level().dimension(), event.getEntity().level(), blockpos), Direction.UP)) {
                                    event.getEntity().level().setBlockAndUpdate(blockpos, blockstate);
                                    event.getEntity().level().scheduleTick(blockpos, ModBlocks.FIRED_OBSIDIAN.get(), Mth.nextInt(event.getEntity().getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void addSquidderTrades(VillagerTradesEvent event){
        if(event.getType() == ModVillagerProfessions.SQUIDDER.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.TENTACLE.get(), 3),
                    new ItemStack(Items.EMERALD, 1),
                    8, 8, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.TENTACLE.get(), 1),
                    8, 8, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(Blocks.MAGMA_BLOCK, 1),
                    15, 6, 0.5f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Blocks.MAGMA_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 1),
                    15, 6, 0.5f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(Items.LAVA_BUCKET, 1),
                    3, 10, 0.2f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.SAW.get(), 1),
                    5, 15, 0.12f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 40),
                    new ItemStack(ModItems.BEAK.get(), 1),
                    3, 25, 0.2f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BEAK.get(), 1),
                    new ItemStack(Items.EMERALD, 30),
                    3, 25, 0.2f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.FIRE_BOOTS.get(), 1),
                    new ItemStack(Items.EMERALD, 64),
                    1, 40, 0.25f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.FIRE_BOOTS.get(), 1),
                    1, 40, 0.25f));

        }
    }
}
