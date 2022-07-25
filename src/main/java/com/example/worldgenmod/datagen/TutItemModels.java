package com.example.worldgenmod.datagen;


import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WorldGenMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.WEIRD_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/weird_ore_deepslate"));
        withExistingParent(Registration.WEIRD_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/weird_ore_overworld"));

        singleTexture(Registration.WEIRD_ORE_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                ("layer0"),
                modLoc("items/weird_ore_ingot"));

        singleTexture(Registration.RAW_WEIRD_ORE_CHUNK.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                ("layer0"),
                modLoc("items/weird_ore_chunk"));
    }
}