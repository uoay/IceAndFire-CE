package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.registry.tag.IafItemTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public enum IafToolMaterials implements ToolMaterial {
    SILVER_TOOL_MATERIAL("silver", 460, 1.0F, 11.0F, 18, BlockTags.INCORRECT_FOR_STONE_TOOL),
    COPPER_TOOL_MATERIAL("copper", 300, 0.0F, 0.7F, 10, BlockTags.INCORRECT_FOR_STONE_TOOL),
    DRAGONBONE_TOOL_MATERIAL("dragon_bone", 1660, 4.0F, 10.0F, 22, BlockTags.INCORRECT_FOR_IRON_TOOL),
    FIRE_DRAGONBONE_TOOL_MATERIAL("fire_dragon_bone", 2000, 5.5F, 10F, 22, BlockTags.INCORRECT_FOR_IRON_TOOL),
    ICE_DRAGONBONE_TOOL_MATERIAL("ice_dragon_bone", 2000, 5.5F, 10F, 22, BlockTags.INCORRECT_FOR_IRON_TOOL),
    LIGHTNING_DRAGONBONE_TOOL_MATERIAL("lightning_dragon_bone", 2000, 5.5F, 10F, 22, BlockTags.INCORRECT_FOR_IRON_TOOL),
    TROLL_WEAPON_TOOL_MATERIAL("troll_weapon", 300, 1F, 10F, 1, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    MYRMEX_CHITIN_TOOL_MATERIAL("myrmex_chitin", 600, 1.0F, 6.0F, 8, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    HIPPOGRYPH_SWORD_TOOL_MATERIAL("hippogryph_sword", 500, 2.5F, 10F, 10, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    STYMHALIAN_SWORD_TOOL_MATERIAL("stymphalian_sword", 500, 2, 10.0F, 10, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    AMPHITHERE_SWORD_TOOL_MATERIAL("amphithere_sword", 500, 1F, 10F, 10, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    HIPPOCAMPUS_SWORD_TOOL_MATERIAL("hippocampus_sword", 500, -2F, 0F, 50, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    DREAD_SWORD_TOOL_MATERIAL("dread_sword", 100, 1F, 10F, 0, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    DREAD_KNIGHT_TOOL_MATERIAL("dread_knight_sword", 1200, 13F, 0F, 10, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    GHOST_SWORD_TOOL_MATERIAL("ghost_sword", 3000, 5, 10.0F, 25, BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    DRAGON_STEEL_FIRE("dragon_steel_fire", 4000, 4F, 10F, 21, BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
    DRAGON_STEEL_ICE("dragon_steel_ice", 4000, 4F, 10F, 21, BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
    DRAGON_STEEL_LIGHTNING("dragon_steel_lightning", 4000, 4F, 10F, 21, BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
    DREAD_QUEEN("dread_queen", 4000, 4F, 10F, 21, BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    private final String name;
    private final int durability;
    private final float damage;
    private final float speed;
    private final int enchantability;
    private final TagKey<Block> inverted;
    private Ingredient ingredient = Ingredient.ofItems(Items.AIR);

    IafToolMaterials(String name, int durability, float damage, float speed, int enchantability, TagKey<Block> inverted) {
        this.name = name;
        this.durability = durability;
        this.damage = damage;
        this.speed = speed;
        this.enchantability = enchantability;
        this.inverted = inverted;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.speed;
    }

    @Override
    public float getAttackDamage() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverted;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.ingredient == null ? Ingredient.EMPTY : this.ingredient;
    }

    public void setRepairMaterial(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public static void init() {
        SILVER_TOOL_MATERIAL.setRepairMaterial(Ingredient.fromTag(IafItemTags.INGOTS_SILVER));
        DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        FIRE_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        ICE_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        LIGHTNING_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        TROLL_WEAPON_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(Items.STONE));
        HIPPOGRYPH_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.HIPPOGRYPH_TALON.get()));
        HIPPOCAMPUS_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.SHINY_SCALES.get()));
        AMPHITHERE_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.AMPHITHERE_FEATHER.get()));
        STYMHALIAN_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.STYMPHALIAN_BIRD_FEATHER.get()));
        MYRMEX_CHITIN_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.MYRMEX_DESERT_CHITIN.get()));
        DREAD_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DREAD_SHARD.get()));
        DREAD_KNIGHT_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DREAD_SHARD.get()));
        COPPER_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(Items.COPPER_INGOT));
        DRAGON_STEEL_FIRE.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGONSTEEL_FIRE_INGOT.get()));
        DRAGON_STEEL_ICE.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGONSTEEL_ICE_INGOT.get()));
        DRAGON_STEEL_LIGHTNING.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGONSTEEL_LIGHTNING_INGOT.get()));
    }
}
