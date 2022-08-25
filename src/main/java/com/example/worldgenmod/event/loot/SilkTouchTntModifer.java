package com.example.worldgenmod.event.loot;

import com.example.worldgenmod.Entities.PrimedMiningExplosives;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.logging.Logger;

import static com.example.worldgenmod.WorldGenMod.LOGGER;

public class SilkTouchTntModifer extends LootModifier {

    public SilkTouchTntModifer(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NonNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        LOGGER.debug("triggered");
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof PrimedMiningExplosives) {
                generatedLoot.clear();
                if (context.getParamOrNull(LootContextParams.BLOCK_STATE) != null) {
                    generatedLoot.add(new ItemStack(context.getParamOrNull((LootContextParams.BLOCK_STATE)).getBlock().asItem(), 1));
                    return generatedLoot;
            }
        }
        return generatedLoot;
    }

    public static class ModifierSerializer extends GlobalLootModifierSerializer<SilkTouchTntModifer> {
        @Override
        public SilkTouchTntModifer read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            return new SilkTouchTntModifer(ailootcondition);
        }

        @Override
        public JsonObject write(SilkTouchTntModifer instance) {
            JsonObject json = makeConditions(instance.conditions);
            return json;
        }
    }
}
