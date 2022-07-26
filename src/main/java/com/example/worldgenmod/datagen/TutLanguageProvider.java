package com.example.worldgenmod.datagen;


import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.example.worldgenmod.setup.ModSetup.TAB_NAME;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(DataGenerator gen, String locale) {
        super(gen, WorldGenMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "WorldGenMod");

        add(Registration.WEIRD_ORE_OVERWORLD.get(), "Weird Ore");
        add(Registration.WEIRD_ORE_DEEPSLATE.get(), "Weird Deepslate Ore");
        add(Registration.WEIRD_ORE_INGOT.get(), "Weird Ingot");
        add(Registration.RAW_WEIRD_ORE_CHUNK.get(), "Weird Raw Chunk");
    }
}