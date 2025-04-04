package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.entity.EntityDeathWorm;
import com.iafenvoy.iceandfire.event.ServerEvents;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;


public interface DragonSteelOverrides<T extends ToolItem> {
    default float getAttackDamage(T item) {
        if (item instanceof SwordItem swordItem)
            return swordItem.getMaterial().getAttackDamage();
        if (item instanceof MiningToolItem toolItem)
            return toolItem.getMaterial().getAttackDamage();
        return item.getMaterial().getAttackDamage();
    }

    static boolean isDragonSteel(ToolMaterial tier) {
        return isDragonSteelFire(tier) || isDragonSteelIce(tier) || isDragonSteelLightning(tier);
    }

    static boolean isDragonSteelFire(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGON_STEEL_FIRE;
    }

    static boolean isDragonSteelIce(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGON_STEEL_ICE;
    }

    static boolean isDragonSteelLightning(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGON_STEEL_LIGHTNING;
    }

    default void hurtEnemy(T item, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (item.getMaterial() == IafToolMaterials.SILVER_TOOL_MATERIAL)
            if (target.getType().isIn(EntityTypeTags.UNDEAD))
                target.damage(attacker.getWorld().getDamageSources().magic(), this.getAttackDamage(item) + 3.0F);

        if (item.getMaterial() == IafToolMaterials.MYRMEX_CHITIN_TOOL_MATERIAL) {
            if (target.getType().isIn(EntityTypeTags.ARTHROPOD))
                target.damage(attacker.getWorld().getDamageSources().generic(), this.getAttackDamage(item) + 5.0F);
            if (target instanceof EntityDeathWorm)
                target.damage(attacker.getWorld().getDamageSources().generic(), this.getAttackDamage(item) + 5.0F);
        }
        if (isDragonSteelFire(item.getMaterial()) && IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
            target.setOnFireFor(15);
            target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
        if (isDragonSteelIce(item.getMaterial()) && IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
            IafEntityData data = IafEntityData.get(target);
            data.frozenData.setFrozen(target, 300);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 2));
            target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
        if (isDragonSteelLightning(item.getMaterial()) && IafCommonConfig.INSTANCE.armors.dragonLightningAbility.getValue()) {
            boolean flag = true;
            if (attacker instanceof PlayerEntity)
                if (attacker.handSwingProgress > 0.2)
                    flag = false;
            if (!attacker.getWorld().isClient && flag) {
                LightningEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(target.getWorld());
                assert lightningboltentity != null;
                lightningboltentity.getCommandTags().add(ServerEvents.BOLT_DONT_DESTROY_LOOT);
                lightningboltentity.getCommandTags().add(attacker.getUuidAsString());
                lightningboltentity.refreshPositionAfterTeleport(target.getPos());
                if (!target.getWorld().isClient)
                    target.getWorld().spawnEntity(lightningboltentity);
            }
            target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
    }

    default void appendHoverText(ToolMaterial tier, List<Text> tooltip) {
        if (tier == IafToolMaterials.SILVER_TOOL_MATERIAL)
            tooltip.add(Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));
        if (tier == IafToolMaterials.MYRMEX_CHITIN_TOOL_MATERIAL)
            tooltip.add(Text.translatable("myrmextools.hurt").formatted(Formatting.GREEN));
        if (isDragonSteelFire(tier)) {
            if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue())
                tooltip.add(Text.translatable("dragon_sword_fire.hurt2").formatted(Formatting.DARK_RED));
        }
        if (isDragonSteelIce(tier)) {
            if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue())
                tooltip.add(Text.translatable("dragon_sword_ice.hurt2").formatted(Formatting.AQUA));
        }
        if (isDragonSteelLightning(tier)) {
            if (IafCommonConfig.INSTANCE.armors.dragonLightningAbility.getValue())
                tooltip.add(Text.translatable("dragon_sword_lightning.hurt2").formatted(Formatting.DARK_PURPLE));
        }
    }
}
