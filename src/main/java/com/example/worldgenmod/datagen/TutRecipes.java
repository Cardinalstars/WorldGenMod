package com.example.worldgenmod.datagen;


import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.WEIRD_ORE_ITEM),
                        Registration.WEIRD_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(Registration.WEIRD_ORE_ITEM))
                .save(consumer,"weird_ingot1");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_WEIRD_ORE_CHUNK.get()),
                        Registration.WEIRD_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_chunk",has(Registration.RAW_WEIRD_ORE_CHUNK.get()))
                .save(consumer,"weird_ingot2");
    }
}
