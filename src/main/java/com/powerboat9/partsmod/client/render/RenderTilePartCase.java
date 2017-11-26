package com.powerboat9.partsmod.client.render;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.tiles.TilePartCase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderTilePartCase extends TileEntitySpecialRenderer<TilePartCase> {
    @Override
    public void render(TilePartCase te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        for (int i = 0; i < te.parts.size(); ++i) {
            MainRender.renderPart(te, te.parts.get(i));
        }

        MainRender.renderPart(te, PartsModMain.partRegistry.getValue(new ResourceLocation(PartsModMain.modid, "pump")));

        GlStateManager.popMatrix();
    }
}