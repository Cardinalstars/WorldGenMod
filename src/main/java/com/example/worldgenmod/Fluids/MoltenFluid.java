package com.example.worldgenmod.Fluids;

import com.example.worldgenmod.setup.fluids.FluidRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MoltenFluid extends FlowingFluid {

    private final FluidRegistration.FluidRegMolten entry;
    private final ResourceLocation stillTex;
    private final ResourceLocation flowingTex;
    @Nullable
    protected final Consumer<FluidAttributes.Builder> buildAttributes;

    public MoltenFluid(ResourceLocation flowingTex, ResourceLocation stillTex, FluidRegistration.FluidRegMolten entry
    , Consumer<FluidAttributes.Builder> buildAttributes) {
        this.entry = entry;
        this.stillTex = stillTex;
        this.flowingTex = flowingTex;
        this.buildAttributes = buildAttributes;
    }

    @Override
    public Fluid getFlowing() {
        return entry.getFlowing();
    }

    @Override
    public Fluid getSource() {
        return entry.getStill();
    }

    @Override
    protected boolean canConvertToSource() {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {

    }

    @Override
    protected int getSlopeFindDistance(LevelReader pLevel) {
        return 4;
    }

    @Override
    protected int getDropOff(LevelReader pLevel) {
        return 1;
    }

    @Override
    public Item getBucket() {
        return entry.getBucket();
    }

    @Override
    protected boolean canBeReplacedWith(FluidState pState, BlockGetter pLevel, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return false;
    }

    @Override
    public int getTickDelay(LevelReader pLevel) {
        return 0;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState pState) {
        BlockState result = entry.getBlock().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
        return result;
    }

    @Override
    public boolean isSource(FluidState pState) {
        return pState.getType()== entry.getStill();
    }

    @Override
    public int getAmount(FluidState pState) {
        if(isSource(pState))
            return 8;
        else
            return pState.getValue(LEVEL);
    }

    @Nonnull
    @Override
    protected FluidAttributes createAttributes()
    {
        FluidAttributes.Builder builder = FluidAttributes.builder(stillTex, flowingTex);
        if(buildAttributes!=null)
            buildAttributes.accept(builder);
        return builder.build(this);
    }

    public static class Flowing extends MoltenFluid{

        public Flowing(ResourceLocation flowingTex, ResourceLocation stillTex, FluidRegistration.FluidRegMolten entry, Consumer<FluidAttributes.Builder> buildAttributes) {
            super(flowingTex, stillTex, entry, buildAttributes);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
    }

}
