package com.powerboat9.partsmod.client.render;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.Part;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class MainRender {
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

    public static void renderModel(IBakedModel model) {
        PartsModMain.mc.getBlockRendererDispatcher().getBlockModelRenderer().renderModelBrightnessColor(model, 1, 1, 1, 1);
    }

    public static void renderPart(double x, double y, double z, float rotX, float rotY, float rotZ, Part part) {
        if (!part.isSimple() && !part.isDynamic()) return;
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(rotX, 1, 0, 0);
        GlStateManager.rotate(rotY, 0, 1, 0);
        GlStateManager.rotate(rotZ, 0, 0, 1);
        GlStateManager.translate(x, y, z);
        if (part.isSimple()) {
            MainRender.renderModel(PartsModMain.mc.getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(part.getRegistryName(), part.modelVariant(null))));
        }
        if (part.isDynamic()) {
            part.render();
        }
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    public static void renderPart(IPartHolder holder, Part part) {
        if (!part.isSimple() && !part.isDynamic()) return;
        int[] partPos = part.getCoords();
        Vec3d ang = holder.getAngle().add(part.getModelRot());
        renderPart(partPos[0], partPos[1], partPos[2], (float) ang.x, (float) ang.y, (float) ang.z, part);
    }
}