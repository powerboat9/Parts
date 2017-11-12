package com.powerboat9.partsmod.client.render;

import com.powerboat9.partsmod.PartsModMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import javax.annotation.Nullable;
import java.util.List;

public class MainRender {
    public static BlockRendererDispatcher blockRendererDispatcher = null;
    public static BlockModelRenderer blockModelRenderer = null;
    public static ModelManager blockModelManager = null;

    public static void drawRectPrism(int x, int y, int z, int dx, int dy, int dz, ResourceLocation up, ResourceLocation down, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        Tessellator t = Tessellator.getInstance();
        RenderManager r = PartsModMain.mc.getRenderManager();
        BufferBuilder b = t.getBuffer();
        TextureManager textureManager = PartsModMain.mc.getTextureManager();

        // Top
        if (up != null) {
            textureManager.bindTexture(up);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(x, dy, z).tex(0, 0).endVertex();
            b.pos(x, dy, dz).tex(0, 0.1).endVertex();
            b.pos(dx, dy, dz).tex(0.1, 0.1).endVertex();
            b.pos(dx, dy, z).tex(0.1, 0).endVertex();
            t.draw();
        }

        // Bottom
        if (down != null) {
            textureManager.bindTexture(down);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(x, y, z).tex(0, 0).endVertex();
            b.pos(dx, y, z).tex(0.1, 0).endVertex();
            b.pos(dx, y, dz).tex(0.1, 0.1).endVertex();
            b.pos(x, y, dz).tex(0, 0.1).endVertex();
            t.draw();
        }

        // North
        if (north != null) {
            textureManager.bindTexture(north);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(x, y, z).tex(0.1, 0.1).endVertex();
            b.pos(x, dy, z).tex(0.1, 0).endVertex();
            b.pos(dx, dy, z).tex(0, 0).endVertex();
            b.pos(dx, y, z).tex(0, 0.1).endVertex();
            t.draw();
        }

        // South
        if (south != null) {
            textureManager.bindTexture(south);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(x, y, dz).tex(0, 0.1).endVertex();
            b.pos(dx, y, dz).tex(0.1, 0.1).endVertex();
            b.pos(dx, dy, dz).tex(0.1, 0).endVertex();
            b.pos(x, dy, dz).tex(0, 0).endVertex();
            t.draw();
        }

        // East
        if (east != null) {
            textureManager.bindTexture(east);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(dx, y, z).tex(0.1, 0.1).endVertex();
            b.pos(dx, dy, z).tex(0, 0.1).endVertex();
            b.pos(dx, dy, dz).tex(0, 0).endVertex();
            b.pos(dx, y, dz).tex(0.1, 0).endVertex();
            t.draw();
        }

        // West
        if (west != null) {
            textureManager.bindTexture(west);
            b.begin(7, DefaultVertexFormats.POSITION_TEX);
            b.pos(x, y, z).tex(0.1, 0).endVertex();
            b.pos(x, y, dz).tex(0.1, 0.1).endVertex();
            b.pos(x, dy, dz).tex(0, 0.1).endVertex();
            b.pos(x, dy, z).tex(0, 0).endVertex();
            t.draw();
        }

        // End
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    @Mod.EventHandler
    public static void cacheRenderVars(FMLPostInitializationEvent event) {
        blockRendererDispatcher = PartsModMain.mc.getBlockRendererDispatcher();
        blockModelRenderer = blockRendererDispatcher.getBlockModelRenderer();
        blockModelManager = blockRendererDispatcher.getBlockModelShapes().getModelManager();
    }

    public static void renderModel(IBakedModel model) {
        PartsModMain.mc.getBlockRendererDispatcher().getBlockModelRenderer().renderModelBrightnessColor(model, 1, 1, 1, 1);
    }
}