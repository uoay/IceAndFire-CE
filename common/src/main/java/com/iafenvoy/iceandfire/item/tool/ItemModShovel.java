package com.iafenvoy.iceandfire.item.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.config.IafCommonConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ItemModShovel extends ShovelItem implements DragonSteelOverrides<ItemModShovel> {
    private Multimap<EntityAttribute, EntityAttributeModifier> dragonsteelModifiers;

    public ItemModShovel(ToolMaterial toolmaterial) {
        super(toolmaterial, new Settings());
    }

//    @Override
//    @Deprecated
//    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
//        return equipmentSlot == EquipmentSlot.MAINHAND && this.isDragonSteel(this.getMaterial()) ? this.bakeDragonsteel() : super.getAttributeModifiers(equipmentSlot);
//    }

    @Override
    @Deprecated
    public Multimap<EntityAttribute, EntityAttributeModifier> bakeDragonsteel() {
        if (this.getMaterial().getAttackDamage() != IafCommonConfig.INSTANCE.armors.dragonSteelBaseDamage.getValue().floatValue() || this.dragonsteelModifiers == null) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE.value(), new EntityAttributeModifier(Identifier.of(IceAndFire.MOD_ID, "shovel"), IafCommonConfig.INSTANCE.armors.dragonSteelBaseDamage.getValue() - 1F + 1.5F, EntityAttributeModifier.Operation.ADD_VALUE));
            builder.put(EntityAttributes.GENERIC_ATTACK_SPEED.value(), new EntityAttributeModifier(Identifier.of(IceAndFire.MOD_ID, "shovel"), -3.0, EntityAttributeModifier.Operation.ADD_VALUE));
            this.dragonsteelModifiers = builder.build();
        }
        return this.dragonsteelModifiers;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        if (this.isDragonSteel(this.getMaterial())) {
            return IafCommonConfig.INSTANCE.armors.dragonSteelBaseDurability.getValue();
        } else {
            return this.getMaterial().getDurability();
        }
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.hurtEnemy(this, stack, target, attacker);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        this.appendHoverText(this.getMaterial(), tooltip);
    }
}
