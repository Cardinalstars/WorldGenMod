package com.example.worldgenmod.datagen;

import com.example.worldgenmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static com.example.worldgenmod.setup.fluids.FluidRegistration.MOLTEN_IRON;

public class FluidTags extends FluidTagsProvider {

    public FluidTags(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Registration.MOLTEN_IRON)
                .add(MOLTEN_IRON.getStill())
                .add(MOLTEN_IRON.getFlowing());
        tag(net.minecraft.tags.FluidTags.LAVA)
                .add(MOLTEN_IRON.getStill())
                .add(MOLTEN_IRON.getFlowing());
    }

    @Override
    public String getName() {
        return "fluidtags";
    }
}
