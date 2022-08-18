package com.example.worldgenmod.event;

import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.event.loot.SilkTouchTntModifer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = WorldGenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                   event) {
        event.getRegistry().registerAll(
                new SilkTouchTntModifer.ModifierSerializer().setRegistryName
                        (new ResourceLocation(WorldGenMod.MODID, "silk_touch_explosion.json"))
        );
    }
}
