package com.example.worldgenmod.setup.fluids;

import com.example.worldgenmod.Fluids.MoltenFluid;
import com.example.worldgenmod.setup.ModSetup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import static com.example.worldgenmod.WorldGenMod.MODID;
import static com.example.worldgenmod.setup.Registration.BLOCKS;
import static com.example.worldgenmod.setup.Registration.ITEMS;

public class FluidRegistration {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static final FluidRegMolten MOLTEN_IRON = new FluidRegMolten("molten_iron", new ResourceLocation("block/lava_still"), new ResourceLocation("block/lava_flow"));

    public static class FluidRegMolten
    {
        private final RegistryObject<MoltenFluid> flowing;
        private final RegistryObject<MoltenFluid> still;
        private final RegistryObject<BucketItem> bucket;
        private final RegistryObject<Block> block;


        private FluidRegMolten(String name, ResourceLocation stillTex, ResourceLocation flowingTex) {
            this(name, stillTex, flowingTex, 0);
        }

        private FluidRegMolten(String name, ResourceLocation stillTex, ResourceLocation flowingTex, int burnTime){

            Mutable<FluidRegMolten> thisMutable = new MutableObject<>();
            this.flowing = FLUIDS.register(name+"_flowing",
                    () -> new MoltenFluid.Flowing(flowingTex, stillTex, thisMutable.getValue(), null));
            this.still = FLUIDS.register(name,
                    () -> new MoltenFluid(flowingTex, stillTex, thisMutable.getValue(), null));
            this.bucket = ITEMS.register(name+"_bucket",
                    () -> new BucketItem(this.still, new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1)));
            this.block = BLOCKS.register(name+"block",
                    () -> new LiquidBlock(() -> this.getStill(), BlockBehaviour.Properties.copy(Blocks.LAVA)));
            thisMutable.setValue(this);
        }

        public MoltenFluid getFlowing() {
            return flowing.get();
        }

        public MoltenFluid getStill() {
            return still.get();
        }

        public BucketItem getBucket() {
            return bucket.get();
        }

        public Block getBlock() {
            return block.get();
        }
    }

}




