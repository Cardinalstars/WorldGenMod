package com.example.worldgenmod.event.loot;

import com.example.worldgenmod.Entities.PrimedMiningTnt;
import com.example.worldgenmod.setup.Registration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class SilkTouchTntModifer extends LootModifier {
    private final Item SilkedItem;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected SilkTouchTntModifer(LootItemCondition[] conditionsIn, Item SilkedItem) {
        super(conditionsIn);
        this.SilkedItem = SilkedItem;
    }

    @NonNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof PrimedMiningTnt) {
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

            Item SilkedItem = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "silk_touch_explosion.json")));
            return new SilkTouchTntModifer(ailootcondition, SilkedItem);
        }

        @Override
        public JsonObject write(SilkTouchTntModifer instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("silk_touch_explosion.json", ForgeRegistries.ITEMS.getKey(instance.SilkedItem).toString());
            return json;
        }
    }
}
