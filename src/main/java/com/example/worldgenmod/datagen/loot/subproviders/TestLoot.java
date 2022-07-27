package com.example.worldgenmod.datagen.loot.subproviders;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class TestLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>  {

    private final Map<ResourceLocation, LootTable.Builder> map = Maps.newHashMap();

    protected static LootTable.Builder createSimpleTable(String name, Item item) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(item));

        return LootTable.lootTable().withPool(builder);
    }

    protected static LootTable.Builder createDropSelfConditional(String name, Block block, LootItemCondition.Builder pConditionBuilder, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                .when(pConditionBuilder)
                .otherwise(pAlternativeEntryBuilder));

        return LootTable.lootTable().withPool(builder);
    }

    protected static LootTable.Builder createSilkTouchDrop(String name, Block block, LootItemCondition.Builder pConditionBuilder, LootPoolEntryContainer.Builder<?> pAlternativeEntryBuilder) {
        return createDropSelfConditional(name, block, )
    }

    protected void addTables(){};

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> p_124179_) {
        this.addTables();
        Set<ResourceLocation> set = Sets.newHashSet();

        for(Block block : getKnownBlocks()) {
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                LootTable.Builder loottable$builder = this.map.remove(resourcelocation);
                if (loottable$builder == null) {
                    throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourcelocation, Registry.BLOCK.getKey(block)));
                }

                p_124179_.accept(resourcelocation, loottable$builder);
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }

    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK;
    }

    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(TestLoot::new, LootContextParamSets.EMPTY) // Loot table builder for the 'empty' parameter set
                //...
        );
    }

    protected void add(Block pBlock, LootTable.Builder pLootTableBuilder) {
        this.map.put(pBlock.getLootTable(), pLootTableBuilder);
    }

    protected void add(Block pBlock, Function<Block, LootTable.Builder> pFactory) {
        this.add(pBlock, pFactory.apply(pBlock));
    }

}
