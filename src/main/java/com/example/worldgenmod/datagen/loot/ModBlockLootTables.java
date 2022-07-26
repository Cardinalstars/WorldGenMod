package com.example.worldgenmod.datagen.loot;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        add(Registration.WEIRD_ORE_DEEPSLATE.get(), (block) -> createOreDrop(Registration.WEIRD_ORE_DEEPSLATE.get(),Registration.RAW_WEIRD_ORE_CHUNK.get()));
        add(Registration.WEIRD_ORE_OVERWORLD.get(), (block) -> createOreDrop(Registration.WEIRD_ORE_OVERWORLD.get(),Registration.RAW_WEIRD_ORE_CHUNK.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
