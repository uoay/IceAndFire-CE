package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public interface IgniteEntityAbility extends Ability {
    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
            target.setOnFireFor(15);
            target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

        }
    }

    @Override
    default void addDescription(List<Text> tooltip) {
        if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
            tooltip.add(Text.translatable("dragon_sword_fire.hurt2").formatted(Formatting.DARK_RED));
        }
    }
}
