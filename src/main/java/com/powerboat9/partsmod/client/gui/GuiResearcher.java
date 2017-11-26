package com.powerboat9.partsmod.client.gui;

import com.powerboat9.partsmod.PartsModMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiResearcher extends GuiScreen {
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(PartsModMain.modid,"textures/gui/research.png");

    public static final int WIDTH = 248;
    public static final int HEIGHT = 166;

    EntityPlayer user;

    public GuiResearcher(EntityPlayer p) {
        user = p;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1, 1, 1,1);
        drawDefaultBackground();
        PartsModMain.mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        drawTexturedModalRect((this.width - WIDTH) / 2, (this.height - HEIGHT) / 2, 0, 0, WIDTH, HEIGHT);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (!user.isEntityAlive() || user.isDead) {
            user.closeScreen();
        }
    }
}
