package com.example.worldgenmod.setup;

import com.example.worldgenmod.WorldGenMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.example.worldgenmod.WorldGenMod.MODID;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static final BlockBehaviour.Properties ORE_PROPS = BlockBehaviour.Properties.of(Material.STONE).strength(2f);
    public static final Item.Properties ITEM_PROPS = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> WEIRD_ORE_OVERWORLD = BLOCKS.register("weird_ore_overworld", () -> new Block(ORE_PROPS));
    public static final RegistryObject<Item> WEIRD_ORE_OVERWORLD_ITEM = fromBlock(WEIRD_ORE_OVERWORLD);

    public static final RegistryObject<Block> WEIRD_ORE_DEEPSLATE = BLOCKS.register("weird_ore_deepslate", () -> new Block(ORE_PROPS));
    public static final RegistryObject<Item> WEIRD_ORE_DEEPSLATE_ITEM = fromBlock(WEIRD_ORE_DEEPSLATE);

    public static final RegistryObject<Item> RAW_WEIRD_ORE_CHUNK = ITEMS.register("weird_ore_chunk", () -> new Item(ITEM_PROPS));
    public static final RegistryObject<Item> WEIRD_ORE_INGOT = ITEMS.register("weird_ore_ingot", () -> new Item(ITEM_PROPS));

    public static final TagKey<Block> WEIRD_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(WorldGenMod.MODID, "weird_ore"));
    public static final TagKey<Item> WEIRD_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(WorldGenMod.MODID, "weird_ore"));

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPS));
    }
}
