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
                (block) -> createFortuneAffectItemDrop(
                        "werid_ore_deepslate_chunk1",
                        Registration.WEIRD_ORE_DEEPSLATE.get(),
                        Registration.RAW_WEIRD_ORE_CHUNK.get(),
                        1, 10));

        this.add(Registration.WEIRD_ORE_OVERWORLD.get(),
                (block) -> createFortuneAffectItemDrop(
                        "werid_ore_deepslate_chunk2",
                        Registration.WEIRD_ORE_OVERWORLD.get(),
                        Registration.RAW_WEIRD_ORE_CHUNK.get(),
                        1, 3));

        this.add(Registration.GENERATOR.get(),
                (block) -> createSimpleTable("generator", Registration.GENERATOR_ITEM.get()));
        this.add(Registration.ORIENTATION_TEST_BLOCK.get(),
                (block) -> createSimpleTable("orientation_test_block", Registration.TEST_BLOCK_ITEM.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
