package com.example.worldgenmod.datagen;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider {

    public TutBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, WorldGenMod.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.WEIRD_ORE_OVERWORLD.get())
                .add(Registration.WEIRD_ORE_DEEPSLATE.get())
                .add(Registration.GENERATOR.get())
                .add(Registration.ORIENTATION_TEST_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.WEIRD_ORE_OVERWORLD.get())
                .add(Registration.WEIRD_ORE_DEEPSLATE.get())
                .add(Registration.GENERATOR.get());
        tag(Tags.Blocks.ORES)
                .add(Registration.WEIRD_ORE_OVERWORLD.get())
                .add(Registration.WEIRD_ORE_DEEPSLATE.get());
        tag(Registration.WEIRD_ORE)
                .add(Registration.WEIRD_ORE_OVERWORLD.get())
                .add(Registration.WEIRD_ORE_DEEPSLATE.get());
    }

    @Override
    public String getName() {
        return "tutorialtags";
    }
}