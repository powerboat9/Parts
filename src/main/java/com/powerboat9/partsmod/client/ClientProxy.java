package com.powerboat9.partsmod.client;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.RegisterTileRenders;
import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.Part;
import com.powerboat9.partsmod.items.PartItem;
import com.powerboat9.partsmod.parts.PartRegistry;
import com.powerboat9.partsmod.proxy.CommonProxy;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.util.Map;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerBlockModels(ItemBlock[] blockItems) {
        super.registerBlockModels(blockItems);
        for (int i = 0; i < blockItems.length; i++) {
            ModelLoader.setCustomModelResourceLocation(blockItems[i], 0, new ModelResourceLocation(PartsModMain.modid + ":" + blockItems[i].getUnlocalizedName().substring(5)));
        }
        RegisterTileRenders.register();
    }

    @Override
    public void registerPartItemModels(PartItem[] partItems) {
        super.registerPartItemModels(partItems);
        for (int i = 0; i < partItems.length; ++i) {
            ModelLoader.setCustomModelResourceLocation(partItems[i], 0, PartRegistry.getResource(partItems[i].getPart()));
        }
    }

    @Override
    public void bakeModels(ModelBakeEvent event) {
        super.bakeModels(event);
        for (Map.Entry<ResourceLocation, Part> entry : PartsModMain.partRegistry.getEntries()) {
            IModel m = null;
            IBakedModel mb;
            try {
                m = ModelLoaderRegistry.getModel(PartRegistry.getResource(entry.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (m != null) {
                mb = m.bake(m.getDefaultState(), DefaultVertexFormats.ITEM, ModelLoader.defaultTextureGetter());
                event.getModelRegistry().putObject(new ModelResourceLocation(entry.getKey().toString(), "normal"), mb);
            } else {
                event.getModelRegistry().putObject(new ModelResourceLocation(entry.getKey().toString(), "normal"), event.getModelManager().getMissingModel());
            }
        }
    }
}
