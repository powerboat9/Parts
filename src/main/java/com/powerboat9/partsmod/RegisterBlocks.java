package com.powerboat9.partsmod;

import com.powerboat9.partsmod.blocks.BlockAssembler;
import com.powerboat9.partsmod.blocks.BlockBase;
import com.powerboat9.partsmod.blocks.BlockPartCase;
import com.powerboat9.partsmod.blocks.BlockResearcher;
import com.powerboat9.partsmod.tiles.TileAssembler;
import com.powerboat9.partsmod.tiles.TilePartCase;
import com.powerboat9.partsmod.tiles.TileResearcher;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = PartsModMain.modid)
public class RegisterBlocks {
    private static TileEntity[] tileEntities = {
            new TilePartCase(),
            new TileResearcher(),
            new TileAssembler()
    };

    private static BlockBase[] blocks = {
            new BlockPartCase(),
            new BlockResearcher(),
            new BlockAssembler()
    };

    private static ItemBlock[] blockItems;
    static {
        blockItems = new ItemBlock[blocks.length];
        for (int i = 0; i < blocks.length; ++i) {
            blockItems[i] = (ItemBlock) new ItemBlock(blocks[i]).setRegistryName(blocks[i].getRegistryName());
        }
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(blocks);
        for (TileEntity tile : tileEntities) {
            GameRegistry.registerTileEntity(tile.getClass(), tile.getClass().getSimpleName());
        }
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(blockItems);
    }

    @SubscribeEvent
    public static void registerBlockModel(final ModelRegistryEvent event) {
        PartsModMain.proxy.registerBlockModels(blockItems);
    }
}