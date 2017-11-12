package com.powerboat9.partsmod.proxy;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.network.PartsGuiHandler;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    public void registerBlockModels(ItemBlock[] blockItems) {
    }

    public void init(FMLInitializationEvent event) {
        System.out.println("Instance is " + ((PartsModMain.instance != null) ? "not" : "") + " null");
        NetworkRegistry.INSTANCE.registerGuiHandler(PartsModMain.instance, new PartsGuiHandler());
    }

    public void bakeModels(ModelBakeEvent event) {
    }
}
