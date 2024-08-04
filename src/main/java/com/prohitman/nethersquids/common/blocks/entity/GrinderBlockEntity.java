package com.prohitman.nethersquids.common.blocks.entity;

import com.prohitman.nethersquids.client.menu.GrinderMenu;
import com.prohitman.nethersquids.core.init.ModBlockEntities;
import com.prohitman.nethersquids.core.init.ModItems;
import com.prohitman.nethersquids.core.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GrinderBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT_1 = 1;
    private static final int OUTPUT_SLOT_2 = 2;
    private static final int OUTPUT_SLOT_3 = 3;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private List<ItemStack> craftingLoot;
    private int soundCooldown = 0;

    public GrinderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> GrinderBlockEntity.this.progress;
                    case 1 -> GrinderBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> GrinderBlockEntity.this.progress = pValue;
                    case 1 -> GrinderBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.nethersquids.grinder");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            return new GrinderMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("grinder_be.progress", progress);

        if (craftingLoot != null) {
            ListTag listTag = new ListTag();
            for (ItemStack stack : craftingLoot) {
                listTag.add(stack.serializeNBT());
            }
            pTag.put("grinder_be.craftingLoot", listTag);
        }

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("grinder_be.progress");

        if (pTag.contains("grinder_be.craftingLoot", Tag.TAG_LIST)) {
            ListTag listTag = pTag.getList("grinder_be.craftingLoot", Tag.TAG_COMPOUND);
            craftingLoot = new ArrayList<>();
            for (int i = 0; i < listTag.size(); i++) {
                craftingLoot.add(ItemStack.of(listTag.getCompound(i)));
            }
        } else {
            craftingLoot = null;
        }
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) {
            if (craftingLoot == null) {
                craftingLoot = generateLoot((ServerLevel) pLevel);
            }

            if (canInsertItemsIntoOutputSlots(craftingLoot)) {
                increaseCraftingProgress();
                setChanged(pLevel, pPos, pState);

                if (hasProgressFinished()) {
                    craftItems();
                    resetProgress();
                }

                this.spawnParticles(pLevel, pPos);
                this.playGrindingSound(pLevel, pPos);
            } else {
                resetProgress();
                soundCooldown = 0;
            }
        } else {
            resetProgress();
            soundCooldown = 0;

        }
    }

    private void resetProgress() {
        progress = 0;
        craftingLoot = null;
    }

    private void craftItems() {
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        insertItemsIntoOutputSlots(craftingLoot);
        craftingLoot = null;
    }

    private boolean canInsertItemsIntoOutputSlots(List<ItemStack> items) {
        for (ItemStack item : items) {
            if (!item.isEmpty()) {
                if (!canInsertItemIntoSlot(item, OUTPUT_SLOT_1) &&
                        !canInsertItemIntoSlot(item, OUTPUT_SLOT_2) &&
                        !canInsertItemIntoSlot(item, OUTPUT_SLOT_3)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canInsertItemIntoSlot(ItemStack item, int slot) {
        ItemStack stackInSlot = this.itemHandler.getStackInSlot(slot);

        if (stackInSlot.isEmpty()) {
            return true;
        } else if (stackInSlot.getItem() == item.getItem() &&
                stackInSlot.getCount() + item.getCount() <= stackInSlot.getMaxStackSize()) {
            return true;
        }

        return false;
    }

    private void insertItemsIntoOutputSlots(List<ItemStack> items) {
        for (ItemStack item : items) {
            if (!item.isEmpty()) {
                if (canInsertItemIntoSlot(item, OUTPUT_SLOT_1)) {
                    insertItemIntoSlot(item, OUTPUT_SLOT_1);
                } else if (canInsertItemIntoSlot(item, OUTPUT_SLOT_2)) {
                    insertItemIntoSlot(item, OUTPUT_SLOT_2);
                } else if (canInsertItemIntoSlot(item, OUTPUT_SLOT_3)) {
                    insertItemIntoSlot(item, OUTPUT_SLOT_3);
                }
            }
        }
    }

    private void insertItemIntoSlot(ItemStack item, int slot) {
        ItemStack stackInSlot = this.itemHandler.getStackInSlot(slot);

        if (stackInSlot.isEmpty()) {
            this.itemHandler.setStackInSlot(slot, item);
        } else {
            stackInSlot.grow(item.getCount());
            this.itemHandler.setStackInSlot(slot, stackInSlot);
        }
    }

    private boolean hasRecipe() {
        ItemStack inputStack = this.itemHandler.getStackInSlot(INPUT_SLOT);
        return !inputStack.isEmpty() && inputStack.getItem() == ModItems.DEAD_SQUID.get();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private List<ItemStack> generateLoot(ServerLevel level){
        ResourceLocation lootTableLocation = new ResourceLocation("nethersquids", "blocks/grinder_loot_table");
        LootTable lootTable = level.getServer().getLootData().getLootTable(lootTableLocation);

        LootParams lootparams = (new LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.worldPosition)).withParameter(LootContextParams.BLOCK_STATE, this.getBlockState()).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).create(LootContextParamSets.BLOCK);

        return lootTable.getRandomItems(lootparams);
    }

    private void spawnParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 particlePos = Vec3.atBottomCenterOf(pos).add(0, 1.0, 0);
            serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, ModItems.DEAD_SQUID.get().getDefaultInstance()), particlePos.x, particlePos.y, particlePos.z, 5, 0.2, 0.2, 0.2, 0.1);
        }
    }

    private void playGrindingSound(Level level, BlockPos pos) {
        if (soundCooldown <= 0) {
            level.playSound(null, pos, ModSounds.GRINDING.get(), SoundSource.BLOCKS, 0.5f, 1.0f);
            soundCooldown = 20;
        } else {
            soundCooldown--;
        }
    }
}
