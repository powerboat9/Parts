package com.powerboat9.partsmod;

import com.powerboat9.partsmod.parts.Part;
import com.powerboat9.partsmod.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid=PartsModMain.modid, name="Intricate Mechanics", version = "1.0.0")
public class PartsModMain {
    public final static String modid = "partsmod";

    @SidedProxy(clientSide = "com.powerboat9.partsmod.proxy.ClientProxy", serverSide = "com.powerboat9.partsmod.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static PartsModMain instance;

    public static Minecraft mc = Minecraft.getMinecraft();

    public static IForgeRegistry<Part> partRegistry;

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
