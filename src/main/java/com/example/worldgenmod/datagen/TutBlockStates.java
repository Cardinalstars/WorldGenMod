package com.example.worldgenmod.datagen;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.example.worldgenmod.client.GeneratorModelLoader.GENERATOR_LOADER;

public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, WorldGenMod.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.WEIRD_ORE_OVERWORLD.get());
        simpleBlock(Registration.WEIRD_ORE_DEEPSLATE.get());

        registerGenerator();
        testOrientationBuilder();
        registerMiningExplosives();
    }

    private void registerGenerator() {
        // Using CustomLoaderBuilder we can define a json file for our model that will use our baked model
        BlockModelBuilder generatorModel = models().getBuilder(Registration.GENERATOR.get().getRegistryName().getPath())
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((blockModelBuilder, helper) -> new CustomLoaderBuilder<BlockModelBuilder>(GENERATOR_LOADER, blockModelBuilder, helper) { })
                .end();
        directionalBlock(Registration.GENERATOR.get(), generatorModel);
    }
    private void testOrientationBuilder() {
        BlockModelBuilder testOrientationModel = models().cube("test_orientation_block",
                modLoc("block/test_block_orientation"),
                modLoc("block/test_block_orientation"),
                modLoc("block/test_front_face"),
                modLoc("block/test_block_orientation"),
                modLoc("block/test_block_orientation"),
                modLoc("block/test_block_orientation"));
        registerOrientationBlock(Registration.ORIENTATION_TEST_BLOCK.get(), testOrientationModel);
    }
    private void registerMiningExplosives() {
        BlockModelBuilder miningExplosivesModel = models().cube("mining_explosives",
                modLoc("block/mining_explosives_bottom"),
                modLoc("block/mining_explosives_top"),
                modLoc("block/mining_explosives_nontarget_side"),
                modLoc("block/mining_explosives_nontarget_side"),
                modLoc("block/mining_explosives_nontarget_side"),
                modLoc("block/mining_explosives_nontarget_side"));
        directionalBlock(Registration.MINING_EXPLOSIVES.get(), miningExplosivesModel);
    }

    //Helpers
    private void registerOrientationBlock(Block block, ModelFile model) {
        VariantBlockStateBuilder bld = getVariantBuilder(block);

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.NORTH_UP)
                .modelForState()
                .modelFile(model)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.EAST_UP)
                .modelForState()
                .modelFile(model)
                .rotationY(90)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.SOUTH_UP)
                .modelForState()
                .modelFile(model)
                .rotationY(180)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.WEST_UP)
                .modelForState()
                .modelFile(model)
                .rotationY(270)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.DOWN_EAST)
                .modelForState()
                .modelFile(model)
                .rotationY(270)
                .rotationX(90)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.DOWN_NORTH)
                .modelForState()
                .modelFile(model)
                .rotationY(180)
                .rotationX(90)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.DOWN_SOUTH)
                .modelForState()
                .modelFile(model)
                .rotationX(90)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.DOWN_WEST)
                .modelForState()
                .modelFile(model)
                .rotationY(270)
                .rotationX(90)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.UP_EAST)
                .modelForState()
                .modelFile(model)
                .rotationY(90)
                .rotationX(270)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.UP_NORTH)
                .modelForState()
                .modelFile(model)
                .rotationY(180)
                .rotationX(270)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.UP_SOUTH)
                .modelForState()
                .modelFile(model)
                .rotationX(270)
                .addModel();

        bld.partialState().with(BlockStateProperties.ORIENTATION, FrontAndTop.UP_WEST)
                .modelForState()
                .modelFile(model)
                .rotationY(90)
                .rotationX(270)
                .addModel();
    }


}