package com.powerboat9.partsmod.proxy;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.RegisterTileRenders;
import com.powerboat9.partsmod.craft.CraftingLoader;
import com.powerboat9.partsmod.items.PartItem;
import com.powerboat9.partsmod.network.PartsGuiHandler;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    public void registerBlockModels(ItemBlock[] blockItems) {
    }

    public void registerPartItemModels(PartItem[] partItems) {
    }

    public void preInit(FMLPreInitializationEvent event) {
        CraftingLoader.loadPartCrafting();
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(PartsModMain.instance, new PartsGuiHandler());
    }

    public void bakeModels(ModelBakeEvent event) {
    }
}
