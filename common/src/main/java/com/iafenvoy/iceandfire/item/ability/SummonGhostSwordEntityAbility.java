package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.entity.EntityGhostSword;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

import static com.iafenvoy.iceandfire.IceAndFire.LOGGER;

public interface SummonGhostSwordEntityAbility extends Ability {
    @Override
    default void active(@Nullable LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity playerEntity) {
            ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);
            if (playerEntity.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                return;
            }
            final AttributeModifiersComponent dmg = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            double totalDmg = 0D;
            for (AttributeModifiersComponent.Entry modifier : dmg.modifiers())
                if (modifier.attribute().equals(EntityAttributes.GENERIC_ATTACK_DAMAGE))
                    totalDmg += modifier.modifier().value();
            playerEntity.playSound(SoundEvents.ENTITY_ZOMBIE_INFECT, 1, 1);
            EntityGhostSword shot = new EntityGhostSword(IafEntities.GHOST_SWORD.get(), playerEntity.getWorld(), playerEntity, totalDmg * 0.5F, stack);
            shot.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1, 0.5f);
            playerEntity.getWorld().spawnEntity(shot);
            stack.damage(1, playerEntity, EquipmentSlot.MAINHAND);
            playerEntity.getItemCooldownManager().set(stack.getItem(), 10);
        }
    }

    @Override
    default void addDescription(List<Text> tooltip) {
        tooltip.add(Text.translatable("item.iceandfire.ghost_sword.desc_0").formatted(Formatting.GRAY));
    }
}
