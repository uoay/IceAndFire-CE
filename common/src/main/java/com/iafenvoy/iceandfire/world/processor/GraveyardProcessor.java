package com.iafenvoy.iceandfire.world.processor;

import com.iafenvoy.iceandfire.registry.IafProcessors;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class GraveyardProcessor extends StructureProcessor {
    public static final GraveyardProcessor INSTANCE = new GraveyardProcessor();
    public static final MapCodec<GraveyardProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    public static BlockState getRandomCobblestone(BlockState prev, Random random) {
        float rand = random.nextFloat();
        if (rand < 0.5) return Blocks.COBBLESTONE.getDefaultState();
        else if (rand < 0.9) return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        else return Blocks.INFESTED_COBBLESTONE.getDefaultState();
    }

    public static BlockState getRandomCrackedBlock(BlockState prev, Random random) {
        float rand = random.nextFloat();
        if (rand < 0.5) return Blocks.STONE_BRICKS.getDefaultState();
        else if (rand < 0.9) return Blocks.CRACKED_STONE_BRICKS.getDefaultState();
        else return Blocks.MOSSY_STONE_BRICKS.getDefaultState();
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo infoIn2, StructurePlacementData settings) {
        Random random = settings.getRandom(infoIn2.pos());
        if (infoIn2.state().getBlock() == Blocks.STONE_BRICKS) {
            BlockState state = getRandomCrackedBlock(null, random);
            return new StructureTemplate.StructureBlockInfo(infoIn2.pos(), state, null);
        }
        if (infoIn2.state().getBlock() == Blocks.COBBLESTONE) {
            BlockState state = getRandomCobblestone(null, random);
            return new StructureTemplate.StructureBlockInfo(infoIn2.pos(), state, null);
        }
        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IafProcessors.GRAVEYARD_PROCESSOR.get();
    }
}
