package com.powerboat9.partsmod;

import com.powerboat9.partsmod.client.render.RenderTilePartCase;
import com.powerboat9.partsmod.tiles.TilePartCase;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegisterTileRenders {
    @SideOnly(value = Side.CLIENT)
    public static void register() {
        ClientRegistry.bindTileEntitySpecialRenderer(TilePartCase.class, new RenderTilePartCase());
    }
}