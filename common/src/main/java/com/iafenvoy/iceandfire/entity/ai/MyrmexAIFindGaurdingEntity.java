package com.iafenvoy.iceandfire.entity.ai;

import com.iafenvoy.iceandfire.entity.EntityMyrmexBase;
import com.iafenvoy.iceandfire.entity.EntityMyrmexSoldier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.util.math.Box;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class MyrmexAIFindGaurdingEntity<T extends EntityMyrmexBase> extends TrackTargetGoal {
    public final EntityMyrmexSoldier myrmex;
    protected final DragonAITargetItems.Sorter theNearestAttackableTargetSorter;
    protected final Predicate<? super EntityMyrmexBase> targetEntitySelector;
    protected EntityMyrmexBase targetEntity;

    public MyrmexAIFindGaurdingEntity(EntityMyrmexSoldier myrmex) {
        super(myrmex, false, false);
        this.theNearestAttackableTargetSorter = new DragonAITargetItems.Sorter(myrmex);
        this.targetEntitySelector = (Predicate<EntityMyrmexBase>) myrmex1 -> !(myrmex1 instanceof EntityMyrmexSoldier) && myrmex1.getGrowthStage() > 1
                && EntityMyrmexBase.haveSameHive(MyrmexAIFindGaurdingEntity.this.myrmex, myrmex1)
                && !myrmex1.isBeingGuarded && myrmex1.needsGaurding();
        this.myrmex = myrmex;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.myrmex.canMove() || this.myrmex.getTarget() != null || this.myrmex.guardingEntity != null)
            return false;
        List<EntityMyrmexBase> list = this.mob.getWorld().getEntitiesByClass(EntityMyrmexBase.class, this.getTargetableArea(this.getFollowRange()), this.targetEntitySelector);
        if (list.isEmpty()) return false;
        else {
            list.sort(this.theNearestAttackableTargetSorter);
            this.myrmex.guardingEntity = list.getFirst();
            return true;
        }
    }

    protected Box getTargetableArea(double targetDistance) {
        return this.mob.getBoundingBox().expand(targetDistance, 4.0D, targetDistance);
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    public static class Sorter implements Comparator<Entity> {
        private final Entity theEntity;

        public Sorter(EntityMyrmexBase theEntityIn) {
            this.theEntity = theEntityIn;
        }

        @Override
        public int compare(Entity p_compare_1_, Entity p_compare_2_) {
            final double d0 = this.theEntity.squaredDistanceTo(p_compare_1_);
            final double d1 = this.theEntity.squaredDistanceTo(p_compare_2_);
            return Double.compare(d0, d1);
        }
    }
}