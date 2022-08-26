package com.example.worldgenmod.Fluids;

import com.example.worldgenmod.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;

public abstract class MoltenIron extends LavaFluid {

    public Fluid getFlowing() {
        return Registration.MOLTEN_IRON_FLOWING.get();
    }

    public Fluid getSource() {
        return Registration.MOLTEN_IRON_SOURCE.get();
    }

    public Item getBucket() {
        return Registration.MOLTEN_IRON_BUCKET.get();
    }

    public BlockState createLegacyBlock(FluidState pState) {
        return Registration.MOLTEN_IRON_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(pState)));
    }

    public boolean isSame(Fluid pFluid) {
        return pFluid == Registration.MOLTEN_IRON_SOURCE.get() || pFluid == Registration.MOLTEN_IRON_FLOWING.get();
    }

    protected void spreadTo(LevelAccessor pLevel, BlockPos pPos, BlockState pBlockState, Direction pDirection, FluidState pFluidState) {
        if (pDirection == Direction.DOWN) {
            FluidState fluidstate = pLevel.getFluidState(pPos);
            if (this.is(Registration.MOLTEN_IRON) && fluidstate.is(FluidTags.WATER)) {
                if (pBlockState.getBlock() instanceof LiquidBlock) {
                    pLevel.setBlock(pPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, Blocks.STONE.defaultBlockState()), 3);
                }

                this.fizz(pLevel, pPos);
                return;
            }
        }

        super.spreadTo(pLevel, pPos, pBlockState, pDirection, pFluidState);
    }

    private void fizz(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.levelEvent(1501, pPos, 0);
    }
    public static class Flowing extends MoltenIron {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(LEVEL);
        }
        public int getAmount(FluidState pState) {
            return pState.getValue(LEVEL);
        }
        public boolean isSource(FluidState pState) {
            return false;
        }
    }

    public static class Source extends MoltenIron {
        public int getAmount(FluidState pState) {
            return 8;
        }
        public boolean isSource(FluidState pState) {
            return true;
        }
    }
}
