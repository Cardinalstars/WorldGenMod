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

import static com.example.worldgenmod.setup.fluids.FluidRegistration.MOLTEN_IRON;

@Mod.EventBusSubscriber(modid = WorldGenMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(MOLTEN_IRON.getBlock(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MOLTEN_IRON.getStill(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MOLTEN_IRON.getFlowing(), RenderType.translucent());
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
