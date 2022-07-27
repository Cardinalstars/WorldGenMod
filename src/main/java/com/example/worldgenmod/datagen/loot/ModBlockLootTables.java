package com.example.worldgenmod.datagen.loot;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.datagen.loot.subproviders.TestLoot;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends TestLoot {

    @Override
    protected void addTables() {

        this.add(Registration.WEIRD_ORE_DEEPSLATE.get(),
                (block) -> createSimpleTable(
                        "werid_ore_deepslate_chunk1",
                        Registration.RAW_WEIRD_ORE_CHUNK.get()));

        this.add(Registration.WEIRD_ORE_OVERWORLD.get(),
                (block) -> createSimpleTable(
                        "werid_ore_deepslate_chunk2",
                        Registration.RAW_WEIRD_ORE_CHUNK.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
