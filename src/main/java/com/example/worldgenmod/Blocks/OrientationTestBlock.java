package com.example.worldgenmod.Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class OrientationTestBlock extends Block {

    public static final String MESSAGE_GENERATOR = "message.generator";

    public OrientationTestBlock() {
        super(Properties.of(Material.METAL).sound(SoundType.ANVIL).strength(2.0f));
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent(MESSAGE_GENERATOR).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction Direction1 = context.getNearestLookingDirection().getOpposite();
        Direction Direction2;
        if (Direction1 == Direction.UP || Direction1 == Direction.DOWN) {
            Direction2 = context.getHorizontalDirection();
        }
        else {
            Direction2 = Direction.UP;
        }
        return defaultBlockState().setValue(BlockStateProperties.ORIENTATION, FrontAndTop.fromFrontAndTop(Direction1, Direction2));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.ORIENTATION);
    }


    }
