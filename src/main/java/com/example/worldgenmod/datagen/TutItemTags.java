package com.example.worldgenmod.datagen;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, WorldGenMod.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.ORES)
                .add(Registration.WEIRD_ORE_DEEPSLATE_ITEM.get())
                .add(Registration.WEIRD_ORE_OVERWORLD_ITEM.get());
        tag(Tags.Items.INGOTS)
                .add(Registration.WEIRD_INGOT.get());
        tag(Registration.WEIRD_ORE_ITEM)
                .add(Registration.WEIRD_ORE_OVERWORLD_ITEM.get())
                .add(Registration.WEIRD_ORE_DEEPSLATE_ITEM.get());
    }

    @Override
    public String getName() {
        return "tutorialtags";
    }
}