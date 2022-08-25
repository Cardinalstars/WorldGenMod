package com.example.worldgenmod.setup;

import com.example.worldgenmod.Blocks.*;
import com.example.worldgenmod.Entities.PrimedMiningExplosives;
import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.event.loot.SilkTouchTntModifer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.example.worldgenmod.WorldGenMod.MODID;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLMS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        FLUIDS.register(bus);
        GLMS.register(bus);
    }

    // Properties Registration
    public static final BlockBehaviour.Properties ORE_PROPS = BlockBehaviour.Properties.of(Material.STONE).strength(2f);
    public static final Item.Properties ITEM_PROPS = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    // Registering Weird Ore and Weird Ore Items
    public static final RegistryObject<Block> WEIRD_ORE_OVERWORLD = BLOCKS.register("weird_ore_overworld", () -> new Block(ORE_PROPS));
    public static final RegistryObject<Item> WEIRD_ORE_OVERWORLD_ITEM = fromBlock(WEIRD_ORE_OVERWORLD);

    public static final RegistryObject<Block> WEIRD_ORE_DEEPSLATE = BLOCKS.register("weird_ore_deepslate", () -> new Block(ORE_PROPS));
    public static final RegistryObject<Item> WEIRD_ORE_DEEPSLATE_ITEM = fromBlock(WEIRD_ORE_DEEPSLATE);

    public static final RegistryObject<Item> RAW_WEIRD_ORE_CHUNK = ITEMS.register("weird_ore_chunk", () -> new Item(ITEM_PROPS));
    public static final RegistryObject<Item> WEIRD_INGOT = ITEMS.register("weird_ore_ingot", () -> new Item(ITEM_PROPS));

    // TestItems
    public static final RegistryObject<OrientationTestBlock> ORIENTATION_TEST_BLOCK = BLOCKS.register("test_block_orientation", OrientationTestBlock::new);
    public static final RegistryObject<Item> TEST_BLOCK_ITEM = fromBlock(ORIENTATION_TEST_BLOCK);

    // Item Tags
    public static final TagKey<Block> WEIRD_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(WorldGenMod.MODID, "weird_ore"));
    public static final TagKey<Item> WEIRD_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(WorldGenMod.MODID, "weird_ore"));

    //Fluid tags
    public static final TagKey<Fluid> MOLTEN_IRON = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(WorldGenMod.MODID, "molten_iron"));


    // Generator Registration
    public static final RegistryObject<GeneratorBlock> GENERATOR = BLOCKS.register("generator", GeneratorBlock::new);
    public static final RegistryObject<Item> GENERATOR_ITEM = fromBlock(GENERATOR);

    // Generator Block Entity Registration
    public static final RegistryObject<BlockEntityType<GeneratorBE>> GENERATOR_BE = BLOCK_ENTITIES.register("generator", () ->
        BlockEntityType.Builder.of(GeneratorBE::new, GENERATOR.get()).build(null));

    // Mining Block Registration
    public static final RegistryObject<MiningExplosives> MINING_EXPLOSIVES = BLOCKS.register("mining_explosives", MiningExplosives::new);
    public static final RegistryObject<Item> MINING_EXPLOSIVES_ITEM = fromBlock(MINING_EXPLOSIVES);

    //Mining Tnt Primed Registration
    public static final RegistryObject<EntityType<PrimedMiningExplosives>> PRIMED_MINING_EXPLOSIVES =
            ENTITIES.register("primed_mining_explosives",
            () -> EntityType.Builder.<PrimedMiningExplosives>of(PrimedMiningExplosives::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10)
                    .fireImmune()
                    .build("primed_mining_explosives"));

    //Fluids Registration
    public static final ResourceLocation LAVA_STILL_RL = new ResourceLocation("block/lava_still");
    public static final ResourceLocation LAVA_FLOWING_RL = new ResourceLocation("block/lava_flow");

    public static final RegistryObject<FlowingFluid> MOLTEN_IRON_SOURCE = FLUIDS.register
            ("molten_iron_source", () -> new ForgeFlowingFluid.Source(Registration.MOLTEN_IRON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MOLTEN_IRON_FLOWING = FLUIDS.register
            ("molten_iron_flowing", () -> new ForgeFlowingFluid.Flowing(Registration.MOLTEN_IRON_PROPERTIES));

    public static final RegistryObject<LiquidBlock> MOLTEN_IRON_FLUID_BLOCK = BLOCKS.register("molten_iron",
            () -> new LiquidBlock(() -> MOLTEN_IRON_SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA).noCollission()
                    .strength(100f).noDrops()));

    public static final RegistryObject<Item> MOLTEN_IRON_BUCKET = ITEMS.register("molten_iron_bucket",
            () -> new BucketItem(MOLTEN_IRON_SOURCE,
                    new Item.Properties().stacksTo(1)));

    public static final ForgeFlowingFluid.Properties MOLTEN_IRON_PROPERTIES = new ForgeFlowingFluid.Properties
            (() -> MOLTEN_IRON_SOURCE.get(), () -> MOLTEN_IRON_FLOWING.get(),
                    FluidAttributes.builder(LAVA_STILL_RL, LAVA_FLOWING_RL).density(15).sound(SoundEvents.LAVA_AMBIENT)
                            .luminosity(2).viscosity(5).color(0xbffcba03)).slopeFindDistance(4).levelDecreasePerBlock(2)
            .bucket(() -> MOLTEN_IRON_BUCKET.get()).block( () -> MOLTEN_IRON_FLUID_BLOCK.get());

    //GLM Registration
    public static final RegistryObject<GlobalLootModifierSerializer<SilkTouchTntModifer>> SILK_TOUCH_TNT_MOD = GLMS.register("mining_explosives",
            () -> new SilkTouchTntModifer.ModifierSerializer());

    //helper
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPS));
    }
}
