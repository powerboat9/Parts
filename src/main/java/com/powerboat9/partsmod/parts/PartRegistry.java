package com.powerboat9.partsmod.parts;

import com.powerboat9.partsmod.PartsModMain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = PartsModMain.modid)
public class PartRegistry {

    public static Part createPart(IPartHolder h, String id) {
        return PartsModMain.partRegistry.getValue(new ResourceLocation(id)).createNew(h);
    }

    @SubscribeEvent
    public static void registerParts(final RegistryEvent.NewRegistry event) {
        RegistryBuilder<Part> b = new RegistryBuilder<>();
        b.setName(new ResourceLocation(PartsModMain.modid, "parts"));
        b.setType(Part.class);
        PartsModMain.partRegistry = b.create();
    }

    @SubscribeEvent
    public static void registerPartModels(ModelBakeEvent event) {
        PartsModMain.proxy.bakeModels(event);
    }
}