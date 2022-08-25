package com.example.worldgenmod.datagen;

import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class FluidTags extends FluidTagsProvider {

    public FluidTags(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Registration.MOLTEN_IRON)
                .add(Registration.MOLTEN_IRON_SOURCE.get())
                .add(Registration.MOLTEN_IRON_FLOWING.get());
    }

    @Override
    public String getName() {
        return "fluidtags";
    }
}
