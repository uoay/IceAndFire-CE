package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.DragonArmor;
import com.iafenvoy.iceandfire.data.SeaSerpent;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.item.ItemDragonHorn;
import com.iafenvoy.iceandfire.item.ItemSummoningCrystal;
import com.iafenvoy.iceandfire.particle.*;
import com.iafenvoy.iceandfire.render.TEISRItemRenderer;
import com.iafenvoy.iceandfire.render.TideTridentRenderer;
import com.iafenvoy.iceandfire.render.TrollWeaponRenderer;
import com.iafenvoy.iceandfire.render.armor.*;
import com.iafenvoy.iceandfire.render.block.*;
import com.iafenvoy.iceandfire.render.entity.*;
import com.iafenvoy.iceandfire.render.item.DeathwormGauntletRenderer;
import com.iafenvoy.iceandfire.render.item.GorgonHeadRenderer;
import com.iafenvoy.iceandfire.render.model.*;
import com.iafenvoy.iceandfire.render.model.animator.FireDragonTabulaModelAnimator;
import com.iafenvoy.iceandfire.render.model.animator.IceDragonTabulaModelAnimator;
import com.iafenvoy.iceandfire.render.model.animator.LightningTabulaDragonAnimator;
import com.iafenvoy.iceandfire.render.model.animator.SeaSerpentTabulaModelAnimator;
import com.iafenvoy.uranus.client.model.util.TabulaModelHandlerHelper;
import com.iafenvoy.uranus.client.render.DynamicItemRenderer;
import com.iafenvoy.uranus.client.render.armor.IArmorRendererBase;
import com.iafenvoy.uranus.util.function.MemorizeSupplier;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class IafRenderers {
    public static final Identifier FIRE_DRAGON = new Identifier(IceAndFire.MOD_ID, "firedragon/firedragon_ground");
    public static final Identifier ICE_DRAGON = new Identifier(IceAndFire.MOD_ID, "icedragon/icedragon_ground");
    public static final Identifier LIGHTNING_DRAGON = new Identifier(IceAndFire.MOD_ID, "lightningdragon/lightningdragon_ground");
    public static final Identifier SEA_SERPENT = new Identifier(IceAndFire.MOD_ID, "seaserpent/seaserpent_base");

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(IafEntities.FIRE_DRAGON, x -> new RenderDragonBase(x, TabulaModelHandlerHelper.getModel(FIRE_DRAGON, new MemorizeSupplier<>(FireDragonTabulaModelAnimator::new)), 0));
        EntityRendererRegistry.register(IafEntities.ICE_DRAGON, manager -> new RenderDragonBase(manager, TabulaModelHandlerHelper.getModel(ICE_DRAGON, new MemorizeSupplier<>(IceDragonTabulaModelAnimator::new)), 1));
        EntityRendererRegistry.register(IafEntities.LIGHTNING_DRAGON, manager -> new RenderLightningDragon(manager, TabulaModelHandlerHelper.getModel(LIGHTNING_DRAGON, new MemorizeSupplier<>(LightningTabulaDragonAnimator::new)), 2));
        EntityRendererRegistry.register(IafEntities.DRAGON_EGG, RenderDragonEgg::new);
        EntityRendererRegistry.register(IafEntities.DRAGON_ARROW, RenderDragonArrow::new);
        EntityRendererRegistry.register(IafEntities.DRAGON_SKULL, RenderDragonSkull::new);
        EntityRendererRegistry.register(IafEntities.FIRE_DRAGON_CHARGE, manager -> new RenderDragonFireCharge(manager, true));
        EntityRendererRegistry.register(IafEntities.ICE_DRAGON_CHARGE, manager -> new RenderDragonFireCharge(manager, false));
        EntityRendererRegistry.register(IafEntities.LIGHTNING_DRAGON_CHARGE, RenderDragonLightningCharge::new);
        EntityRendererRegistry.register(IafEntities.HIPPOGRYPH_EGG, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(IafEntities.HIPPOGRYPH, RenderHippogryph::new);
        EntityRendererRegistry.register(IafEntities.STONE_STATUE, RenderStoneStatue::new);
        EntityRendererRegistry.register(IafEntities.GORGON, RenderGorgon::new);
        EntityRendererRegistry.register(IafEntities.PIXIE, RenderPixie::new);
        EntityRendererRegistry.register(IafEntities.CYCLOPS, RenderCyclops::new);
        EntityRendererRegistry.register(IafEntities.SIREN, RenderSiren::new);
        EntityRendererRegistry.register(IafEntities.HIPPOCAMPUS, RenderHippocampus::new);
        EntityRendererRegistry.register(IafEntities.DEATH_WORM, RenderDeathWorm::new);
        EntityRendererRegistry.register(IafEntities.DEATH_WORM_EGG, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(IafEntities.COCKATRICE, RenderCockatrice::new);
        EntityRendererRegistry.register(IafEntities.COCKATRICE_EGG, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(IafEntities.STYMPHALIAN_BIRD, RenderStymphalianBird::new);
        EntityRendererRegistry.register(IafEntities.STYMPHALIAN_FEATHER, RenderStymphalianFeather::new);
        EntityRendererRegistry.register(IafEntities.STYMPHALIAN_ARROW, RenderStymphalianArrow::new);
        EntityRendererRegistry.register(IafEntities.TROLL, RenderTroll::new);
        EntityRendererRegistry.register(IafEntities.MYRMEX_WORKER, manager -> new RenderMyrmexBase(manager, new ModelMyrmexWorker(), 0.5F));
        EntityRendererRegistry.register(IafEntities.MYRMEX_SOLDIER, manager -> new RenderMyrmexBase(manager, new ModelMyrmexSoldier(), 0.75F));
        EntityRendererRegistry.register(IafEntities.MYRMEX_QUEEN, manager -> new RenderMyrmexBase(manager, new ModelMyrmexQueen(), 1.25F));
        EntityRendererRegistry.register(IafEntities.MYRMEX_EGG, RenderMyrmexEgg::new);
        EntityRendererRegistry.register(IafEntities.MYRMEX_SENTINEL, manager -> new RenderMyrmexBase(manager, new ModelMyrmexSentinel(), 0.85F));
        EntityRendererRegistry.register(IafEntities.MYRMEX_ROYAL, manager -> new RenderMyrmexBase(manager, new ModelMyrmexRoyal(), 0.75F));
        EntityRendererRegistry.register(IafEntities.MYRMEX_SWARMER, manager -> new RenderMyrmexBase(manager, new ModelMyrmexRoyal(), 0.25F));
        EntityRendererRegistry.register(IafEntities.AMPHITHERE, RenderAmphithere::new);
        EntityRendererRegistry.register(IafEntities.AMPHITHERE_ARROW, RenderAmphithereArrow::new);
        EntityRendererRegistry.register(IafEntities.SEA_SERPENT, manager -> new RenderSeaSerpent(manager, TabulaModelHandlerHelper.getModel(SEA_SERPENT, SeaSerpentTabulaModelAnimator::new)));
        EntityRendererRegistry.register(IafEntities.SEA_SERPENT_BUBBLES, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.SEA_SERPENT_ARROW, RenderSeaSerpentArrow::new);
        EntityRendererRegistry.register(IafEntities.CHAIN_TIE, RenderChainTie::new);
        EntityRendererRegistry.register(IafEntities.PIXIE_CHARGE, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.TIDE_TRIDENT, RenderTideTrident::new);
        EntityRendererRegistry.register(IafEntities.MOB_SKULL, manager -> new RenderMobSkull(manager, TabulaModelHandlerHelper.getModel(SEA_SERPENT, SeaSerpentTabulaModelAnimator::new)));
        EntityRendererRegistry.register(IafEntities.DREAD_SCUTTLER, RenderDreadScuttler::new);
        EntityRendererRegistry.register(IafEntities.DREAD_GHOUL, RenderDreadGhoul::new);
        EntityRendererRegistry.register(IafEntities.DREAD_BEAST, RenderDreadBeast::new);
        EntityRendererRegistry.register(IafEntities.DREAD_SCUTTLER, RenderDreadScuttler::new);
        EntityRendererRegistry.register(IafEntities.DREAD_THRALL, RenderDreadThrall::new);
        EntityRendererRegistry.register(IafEntities.DREAD_LICH, RenderDreadLich::new);
        EntityRendererRegistry.register(IafEntities.DREAD_LICH_SKULL, RenderDreadLichSkull::new);
        EntityRendererRegistry.register(IafEntities.DREAD_KNIGHT, RenderDreadKnight::new);
        EntityRendererRegistry.register(IafEntities.DREAD_HORSE, RenderDreadHorse::new);
        EntityRendererRegistry.register(IafEntities.HYDRA, RenderHydra::new);
        EntityRendererRegistry.register(IafEntities.HYDRA_BREATH, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.HYDRA_ARROW, RenderHydraArrow::new);
        EntityRendererRegistry.register(IafEntities.SLOW_MULTIPART, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.DRAGON_MULTIPART, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.CYCLOPS_MULTIPART, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.HYDRA_MULTIPART, RenderNothing::new);
        EntityRendererRegistry.register(IafEntities.GHOST, RenderGhost::new);
        EntityRendererRegistry.register(IafEntities.GHOST_SWORD, RenderGhostSword::new);
    }

    public static void registerParticleRenderers() {
        //Fire
        ParticleProviderRegistry.register(IafParticles.DRAGON_FLAME_1, spirit -> ParticleDragonFlame.provider(spirit, 1));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FLAME_2, spirit -> ParticleDragonFlame.provider(spirit, 2));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FLAME_3, spirit -> ParticleDragonFlame.provider(spirit, 3));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FLAME_4, spirit -> ParticleDragonFlame.provider(spirit, 4));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FLAME_5, spirit -> ParticleDragonFlame.provider(spirit, 5));
        //Ice
        ParticleProviderRegistry.register(IafParticles.DRAGON_FROST_1, spirit -> ParticleDragonFrost.provider(spirit, 1));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FROST_2, spirit -> ParticleDragonFrost.provider(spirit, 2));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FROST_3, spirit -> ParticleDragonFrost.provider(spirit, 3));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FROST_4, spirit -> ParticleDragonFrost.provider(spirit, 4));
        ParticleProviderRegistry.register(IafParticles.DRAGON_FROST_5, spirit -> ParticleDragonFrost.provider(spirit, 5));
        //Others
        ParticleProviderRegistry.register(IafParticles.BLOOD, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleBlood(world, x, y, z));
        ParticleProviderRegistry.register(IafParticles.DREAD_PORTAL, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleDreadPortal(world, x, y, z, velocityX, velocityY, velocityZ, 1));
        ParticleProviderRegistry.register(IafParticles.DREAD_TORCH, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleDreadTorch(world, x, y, z, velocityX, velocityY, velocityZ, 1));
        ParticleProviderRegistry.register(IafParticles.GHOST_APPEARANCE, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleGhostAppearance(world, x, y, z, 1));
        ParticleProviderRegistry.register(IafParticles.HYDRA_BREATH, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleHydraBreath(world, x, y, z, 1, 1, 1));
        ParticleProviderRegistry.register(IafParticles.PIXIE_DUST, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticlePixieDust(world, x, y, z, 1, 1, 1, 1));
        ParticleProviderRegistry.register(IafParticles.SERPENT_BUBBLE, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleSerpentBubble(world, x, y, z, velocityX, velocityY, velocityZ, 1));
        ParticleProviderRegistry.register(IafParticles.SIREN_APPEARANCE, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleSirenAppearance(world, x, y, z, 1));
        ParticleProviderRegistry.register(IafParticles.SIREN_MUSIC, (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new ParticleSirenMusic(world, x, y, z, velocityX, velocityY, velocityZ));
    }

    public static void registerBlockEntityRenderers() {
        BlockEntityRendererRegistry.register(IafBlockEntities.PODIUM.get(), RenderPodium::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.IAF_LECTERN.get(), RenderLectern::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.EGG_IN_ICE.get(), RenderEggInIce::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.PIXIE_HOUSE.get(), RenderPixieHouse::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.PIXIE_JAR.get(), ctx -> new RenderJar<>());
        BlockEntityRendererRegistry.register(IafBlockEntities.DREAD_PORTAL.get(), RenderDreadPortal::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.DREAD_SPAWNER.get(), RenderDreadSpawner::new);
        BlockEntityRendererRegistry.register(IafBlockEntities.GHOST_CHEST.get(), RenderGhostChest::new);
    }

    public static void registerArmorRenderers() {
        IArmorRendererBase.register(new CopperArmorRenderer(), IafItems.COPPER_HELMET.get(), IafItems.COPPER_CHESTPLATE.get(), IafItems.COPPER_LEGGINGS.get(), IafItems.COPPER_BOOTS.get());
        IArmorRendererBase.register(new DeathWormArmorRenderer(), IafItems.DEATHWORM_WHITE_HELMET.get(), IafItems.DEATHWORM_WHITE_CHESTPLATE.get(), IafItems.DEATHWORM_WHITE_LEGGINGS.get(), IafItems.DEATHWORM_WHITE_BOOTS.get());
        IArmorRendererBase.register(new DeathWormArmorRenderer(), IafItems.DEATHWORM_YELLOW_HELMET.get(), IafItems.DEATHWORM_YELLOW_CHESTPLATE.get(), IafItems.DEATHWORM_YELLOW_LEGGINGS.get(), IafItems.DEATHWORM_YELLOW_BOOTS.get());
        IArmorRendererBase.register(new DeathWormArmorRenderer(), IafItems.DEATHWORM_RED_HELMET.get(), IafItems.DEATHWORM_RED_CHESTPLATE.get(), IafItems.DEATHWORM_RED_LEGGINGS.get(), IafItems.DEATHWORM_RED_BOOTS.get());
        IArmorRendererBase.register(new DragonSteelArmorRenderer(), IafItems.DRAGONSTEEL_FIRE_HELMET.get(), IafItems.DRAGONSTEEL_FIRE_CHESTPLATE.get(), IafItems.DRAGONSTEEL_FIRE_LEGGINGS.get(), IafItems.DRAGONSTEEL_FIRE_BOOTS.get());
        IArmorRendererBase.register(new DragonSteelArmorRenderer(), IafItems.DRAGONSTEEL_ICE_HELMET.get(), IafItems.DRAGONSTEEL_ICE_CHESTPLATE.get(), IafItems.DRAGONSTEEL_ICE_LEGGINGS.get(), IafItems.DRAGONSTEEL_ICE_BOOTS.get());
        IArmorRendererBase.register(new DragonSteelArmorRenderer(), IafItems.DRAGONSTEEL_LIGHTNING_HELMET.get(), IafItems.DRAGONSTEEL_LIGHTNING_CHESTPLATE.get(), IafItems.DRAGONSTEEL_LIGHTNING_LEGGINGS.get(), IafItems.DRAGONSTEEL_LIGHTNING_BOOTS.get());
        IArmorRendererBase.register(new SilverArmorRenderer(), IafItems.SILVER_HELMET.get(), IafItems.SILVER_CHESTPLATE.get(), IafItems.SILVER_LEGGINGS.get(), IafItems.SILVER_BOOTS.get());
        for (DragonArmor armor : DragonArmor.values())
            IArmorRendererBase.register(new ScaleArmorRenderer(), armor.helmet.get(), armor.chestplate.get(), armor.leggings.get(), armor.boots.get());
        for (SeaSerpent seaSerpent : SeaSerpent.values())
            IArmorRendererBase.register(new SeaSerpentArmorRenderer(), seaSerpent.helmet.get(), seaSerpent.chestplate.get(), seaSerpent.leggings.get(), seaSerpent.boots.get());
        for (TrollType troll : TrollType.values())
            IArmorRendererBase.register(new TrollArmorRenderer(), troll.helmet.get(), troll.chestplate.get(), troll.leggings.get(), troll.boots.get());
    }

    public static void registerItemRenderers() {
        DynamicItemRenderer.RENDERERS.put(IafItems.DEATHWORM_GAUNTLET_RED.get(), new DeathwormGauntletRenderer());
        DynamicItemRenderer.RENDERERS.put(IafItems.DEATHWORM_GAUNTLET_YELLOW.get(), new DeathwormGauntletRenderer());
        DynamicItemRenderer.RENDERERS.put(IafItems.DEATHWORM_GAUNTLET_WHITE.get(), new DeathwormGauntletRenderer());
        DynamicItemRenderer.RENDERERS.put(IafItems.GORGON_HEAD.get(), new GorgonHeadRenderer());
        DynamicItemRenderer.RENDERERS.put(IafItems.TIDE_TRIDENT.get(), new TideTridentRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_BIRCH.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_OAK.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_DARK_OAK.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_SPRUCE.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_MUSHROOM_RED.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.PIXIE_HOUSE_MUSHROOM_BROWN.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.DREAD_PORTAL.get().asItem(), new TEISRItemRenderer());
        DynamicItemRenderer.RENDERERS.put(IafBlocks.GHOST_CHEST.get().asItem(), new TEISRItemRenderer());
        for (TrollType.BuiltinWeapon weapon : TrollType.BuiltinWeapon.values())
            DynamicItemRenderer.RENDERERS.put(weapon.getItem(), new TrollWeaponRenderer());
    }

    public static void registerRenderLayers() {
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.GOLD_PILE.get(), IafBlocks.SILVER_PILE.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.LECTERN.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.PODIUM_OAK.get(), IafBlocks.PODIUM_BIRCH.get(), IafBlocks.PODIUM_SPRUCE.get(), IafBlocks.PODIUM_JUNGLE.get(), IafBlocks.PODIUM_ACACIA.get(), IafBlocks.PODIUM_DARK_OAK.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.FIRE_LILY.get(), IafBlocks.FROST_LILY.get(), IafBlocks.LIGHTNING_LILY.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DRAGON_ICE_SPIKES.get());
        RenderTypeRegistry.register(RenderLayer.getTranslucent(), IafBlocks.MYRMEX_DESERT_RESIN_BLOCK.get(), IafBlocks.MYRMEX_DESERT_RESIN_GLASS.get(), IafBlocks.MYRMEX_JUNGLE_RESIN_BLOCK.get(), IafBlocks.MYRMEX_JUNGLE_RESIN_GLASS.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.MYRMEX_DESERT_BIOLIGHT.get(), IafBlocks.MYRMEX_JUNGLE_BIOLIGHT.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DREAD_STONE_FACE.get());
        RenderTypeRegistry.register(RenderLayer.getTranslucent(), IafBlocks.EGG_IN_ICE.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.JAR_EMPTY.get(), IafBlocks.JAR_PIXIE_0.get(), IafBlocks.JAR_PIXIE_1.get(), IafBlocks.JAR_PIXIE_2.get(), IafBlocks.JAR_PIXIE_3.get(), IafBlocks.JAR_PIXIE_4.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.PIXIE_HOUSE_MUSHROOM_BROWN.get(), IafBlocks.PIXIE_HOUSE_MUSHROOM_RED.get(), IafBlocks.PIXIE_HOUSE_OAK.get(), IafBlocks.PIXIE_HOUSE_BIRCH.get(), IafBlocks.PIXIE_HOUSE_SPRUCE.get(), IafBlocks.PIXIE_HOUSE_DARK_OAK.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DREAD_SPAWNER.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DREAD_TORCH.get(), IafBlocks.BURNT_TORCH.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DREAD_TORCH_WALL.get(), IafBlocks.BURNT_TORCH_WALL.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), IafBlocks.DREADWOOD_LEAVES.get(), IafBlocks.DREADWOOD_SAPLING.get());
    }

    public static void registerModelPredicates() {
        ItemPropertiesRegistry.register(IafItems.DRAGON_BOW.get(), new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1 : 0);
        ItemPropertiesRegistry.register(IafItems.DRAGON_BOW.get(), new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity == null ? 0 : livingEntity.getActiveItem() != itemStack ? 0 : (float) (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20);

        ItemPropertiesRegistry.register(IafItems.DRAGON_HORN.get(), new Identifier("iceorfire"), (stack, level, entity, p) -> ItemDragonHorn.getDragonType(stack) * 0.25F);
        ItemPropertiesRegistry.register(IafItems.SUMMONING_CRYSTAL_FIRE.get(), new Identifier("has_dragon"), (stack, level, entity, p) -> ItemSummoningCrystal.hasDragon(stack) ? 1.0F : 0.0F);
        ItemPropertiesRegistry.register(IafItems.SUMMONING_CRYSTAL_ICE.get(), new Identifier("has_dragon"), (stack, level, entity, p) -> ItemSummoningCrystal.hasDragon(stack) ? 1.0F : 0.0F);
        ItemPropertiesRegistry.register(IafItems.SUMMONING_CRYSTAL_LIGHTNING.get(), new Identifier("has_dragon"), (stack, level, entity, p) -> ItemSummoningCrystal.hasDragon(stack) ? 1.0F : 0.0F);
        ItemPropertiesRegistry.register(IafItems.TIDE_TRIDENT.get(), new Identifier("throwing"), (stack, level, entity, p) -> entity != null && entity.isUsingItem() && entity.getMainHandStack() == stack ? 1.0F : 0.0F);
    }
}
