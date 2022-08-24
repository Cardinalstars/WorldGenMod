package com.example.worldgenmod.setup;


import com.example.worldgenmod.Entities.PrimedMiningExplosivesRenderer;
import com.example.worldgenmod.WorldGenMod;
import com.example.worldgenmod.client.GeneratorModelLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WorldGenMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.MOLTEN_IRON_FLUID_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.MOLTEN_IRON_SOURCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.MOLTEN_IRON_FLOWING.get(), RenderType.translucent());
    }
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(GeneratorModelLoader.GENERATOR_LOADER, new GeneratorModelLoader());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.PRIMED_MINING_EXPLOSIVES.get(), PrimedMiningExplosivesRenderer::new);
    }
}
