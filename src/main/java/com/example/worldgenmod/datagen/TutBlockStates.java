package com.example.worldgenmod.datagen;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
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
    }

    private void registerGenerator() {
        // Using CustomLoaderBuilder we can define a json file for our model that will use our baked model
        BlockModelBuilder generatorModel = models().getBuilder(Registration.GENERATOR.get().getRegistryName().getPath())
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((blockModelBuilder, helper) -> new CustomLoaderBuilder<BlockModelBuilder>(GENERATOR_LOADER, blockModelBuilder, helper) { })
                .end();
        directionalBlock(Registration.GENERATOR.get(), generatorModel);
    }
}