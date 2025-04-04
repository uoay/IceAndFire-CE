package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.entity.block.BlockEntityDragonForge;
import com.iafenvoy.iceandfire.entity.block.BlockEntityDragonForgeBrick;
import com.iafenvoy.iceandfire.item.block.util.IDragonProof;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockDragonForgeBricks extends BlockWithEntity implements IDragonProof {
    public static final BooleanProperty GRILL = BooleanProperty.of("grill");
    private final int isFire;

    public BlockDragonForgeBricks(int isFire) {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).dynamicBounds().strength(40, 500).sounds(BlockSoundGroup.METAL));
        this.isFire = isFire;
        this.setDefaultState(this.getStateManager().getDefaultState().with(GRILL, Boolean.FALSE));
    }

    public static String name(int dragonType) {
        return "dragonforge_%s_brick".formatted(DragonType.getNameFromInt(dragonType));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (this.getConnectedTileEntity(world, pos) != null) {
            BlockEntityDragonForge forge = this.getConnectedTileEntity(world, pos);
            if (forge != null && forge.getPropertyDelegate().fireType == this.isFire) {
                if (!world.isClient) {
                    NamedScreenHandlerFactory inamedcontainerprovider = this.createScreenHandlerFactory(forge.getCachedState(), world, forge.getPos());
                    if (inamedcontainerprovider != null)
                        player.openHandledScreen(inamedcontainerprovider);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    private BlockEntityDragonForge getConnectedTileEntity(World worldIn, BlockPos pos) {
        for (Direction facing : Direction.values())
            if (worldIn.getBlockEntity(pos.offset(facing)) != null && worldIn.getBlockEntity(pos.offset(facing)) instanceof BlockEntityDragonForge forge)
                if (forge.assembled())
                    return forge;
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GRILL);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return MapCodec.unit(this);//TODO: Codec
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityDragonForgeBrick(pos, state);
    }
}
