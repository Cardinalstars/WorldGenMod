package com.example.worldgenmod.datagen;

import com.example.worldgenmod.event.loot.SilkTouchTntModifer;
import com.example.worldgenmod.setup.Registration;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class GLMProvider extends GlobalLootModifierProvider {
    public GLMProvider(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    @Override
    protected void start() {
        this.add("mining_explosives", Registration.SILK_TOUCH_TNT_MOD.get(), new SilkTouchTntModifer(
                new LootItemCondition[] {
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(Registration.PRIMED_MINING_EXPLOSIVES.get())).build()
                }
        ));
    }
}
