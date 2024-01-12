package com.ender09.block_coding.content.network_transceiver;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class NetworkTransceiverBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public NetworkTransceiverBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(3, 3, 0, 13, 13, 2);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pState.hasBlockEntity()) return InteractionResult.FAIL;
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if(blockEntity instanceof NetworkTransceiverBlockEntity networkTransceiverBlockEntity) {
            //Open Transceiver screen
            networkTransceiverBlockEntity.setNetwork(null);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pState.hasBlockEntity()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof NetworkTransceiverBlockEntity networkTransceiverBlockEntity) {
                networkTransceiverBlockEntity.removeDeviceFromNetwork();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);

        if(pNeighborPos == pPos.relative(pState.getValue(FACING).getOpposite())) return;
        Block neighborBlock = pLevel.getBlockState(pNeighborPos).getBlock();
        if(pNeighborBlock == neighborBlock) return;
        if(!pState.hasBlockEntity()) return;

        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if(blockEntity instanceof NetworkTransceiverBlockEntity networkTransceiverBlockEntity) {
            networkTransceiverBlockEntity.removeDeviceFromNetwork();
            networkTransceiverBlockEntity.addDeviceToNetwork();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getClickedFace());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new NetworkTransceiverBlockEntity(pPos, pState);
    }
}
