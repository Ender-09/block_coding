package com.ender09.block_coding.content.network_cable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class NetworkCableBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<NetworkCableSide> NORTH = EnumProperty.create("north", NetworkCableSide.class);
    public static final EnumProperty<NetworkCableSide> SOUTH = EnumProperty.create("south", NetworkCableSide.class);
    public static final EnumProperty<NetworkCableSide> EAST = EnumProperty.create("east", NetworkCableSide.class);
    public static final EnumProperty<NetworkCableSide> WEST = EnumProperty.create("west", NetworkCableSide.class);
    public static final EnumProperty<NetworkCableSide> UP = EnumProperty.create("up", NetworkCableSide.class);
    public static final EnumProperty<NetworkCableSide> DOWN = EnumProperty.create("down", NetworkCableSide.class);

    public NetworkCableBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.UP).setValue(NORTH, NetworkCableSide.NONE).setValue(SOUTH, NetworkCableSide.NONE).setValue(EAST, NetworkCableSide.NONE).setValue(WEST, NetworkCableSide.NONE).setValue(UP, NetworkCableSide.NONE).setValue(DOWN, NetworkCableSide.NONE));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }
}
