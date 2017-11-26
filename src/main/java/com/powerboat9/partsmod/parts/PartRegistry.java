package com.powerboat9.partsmod.parts;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.items.PartItem;
import com.powerboat9.partsmod.parts.input.PartPump;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = PartsModMain.modid)
public class PartRegistry {
    /* From ModelBakery */
    private static final ModelResourceLocation MODEL_MISSING = new ModelResourceLocation("builtin/missing", "missing");
    /* END */

    private static Part[] parts = {
            new PartPump(null)
    };

    private static PartItem[] partItems = {
    };

    public static Part createPart(IPartHolder h, String id) {
        return PartsModMain.partRegistry.getValue(new ResourceLocation(id)).createNew(h);
    }

    @SubscribeEvent
    public static void registerPartRegistry(final RegistryEvent.NewRegistry event) {
        RegistryBuilder<Part> b = new RegistryBuilder<>();
        b.setName(new ResourceLocation(PartsModMain.modid, "parts"));
        b.setType(Part.class);
        PartsModMain.partRegistry = b.create();
    }

    @SubscribeEvent
    public static void registerParts(final RegistryEvent.Register<Part> event) {
        event.getRegistry().registerAll(parts);
    }

    @SubscribeEvent
    public static void registerPartItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(partItems);
    }

    @SubscribeEvent
    public static void bakePartModels(ModelBakeEvent event) {
        PartsModMain.proxy.bakeModels(event);
    }

    @SubscribeEvent
    public static void registerPartItemModels(ModelRegistryEvent event) {
        PartsModMain.proxy.registerPartItemModels(partItems);
    }

    private static ModelResourceLocation getResource(ResourceLocation partLoc, String varient) {
        return new ModelResourceLocation(partLoc.getResourceDomain() + ":parts/" + partLoc.getResourcePath(), varient);
    }

    public static ModelResourceLocation getResource(Part part) {
        return getResource(part.getRegistryName(), part.modelVariant(null));
    }

    public static ModelResourceLocation getResource(ItemStack stack) {
        if (stack.getItem() instanceof PartItem) {
            Part p = ((PartItem) stack.getItem()).getPart();
            return getResource(p.getRegistryName(), p.modelVariant(stack));
        }
        return MODEL_MISSING;
    }
}