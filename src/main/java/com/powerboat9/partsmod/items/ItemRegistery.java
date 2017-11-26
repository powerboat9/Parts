package com.powerboat9.partsmod.items;

import com.google.common.eventbus.Subscribe;
import com.powerboat9.partsmod.PartsModMain;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = PartsModMain.modid)
public class ItemRegistery {
    @Subscribe
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new PartItem(null));
    }
}