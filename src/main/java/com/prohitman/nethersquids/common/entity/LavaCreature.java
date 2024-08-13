package com.prohitman.nethersquids.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

public class LavaCreature extends PathfinderMob {
    protected LavaCreature(EntityType<? extends LavaCreature> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return type == NeoForgeMod.LAVA_TYPE.value();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getAmbientSoundInterval() {
        return 120;
    }

    public int getBaseExperienceReward() {
        return 1 + this.level().random.nextInt(3);
    }

    protected void handleAirSupply(int pAirSupply) {
        if (this.isAlive() && !this.isInLava()) {
            this.setAirSupply(pAirSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }

    }

    /**
     * Gets called every tick from main Entity class
     */
    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed() {
        return false;
    }
}
