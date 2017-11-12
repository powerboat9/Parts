package com.powerboat9.partsmod.client.render;

import com.powerboat9.partsmod.parts.Part;
import com.powerboat9.partsmod.tiles.TilePartCase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTilePartCase extends TileEntitySpecialRenderer<TilePartCase> {
    @Override
    public void render(TilePartCase te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        for (int i = 0; i < te.parts.size(); ++i) {
            Part p = te.parts.get(i);
            int loc[] = p.getCoords(); // for something non dynamic with no set texture, return null
            if (p.isSimple()) {
                GlStateManager.pushAttrib();
                GlStateManager.pushMatrix();
                GlStateManager.translate(loc[0], loc[1], loc[2]);
                try {
                    MainRender.renderModel(MainRender.blockModelManager.getModel(new ModelResourceLocation(p.getRegistryName(), "normal")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                GlStateManager.popMatrix();
                GlStateManager.popAttrib();
            }
            if (p.isDynamic()) { // Does not change pos for dynamic
                p.render();
            }
        }

        GlStateManager.popMatrix();
    }
}