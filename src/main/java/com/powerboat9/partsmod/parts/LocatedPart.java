package com.powerboat9.partsmod.parts;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;

public abstract class LocatedPart extends Part {
    IPartHolder hold;
    int x, y, z;

    public LocatedPart(IPartHolder partHolder) {
        hold = partHolder;
    }

    @Override
    public void loadPart(NBTTagCompound nbt) {
        int[] loc = nbt.getIntArray("pos");
        x = loc[0];
        y = loc[1];
        z = loc[2];
    }

    @Override
    public NBTTagCompound savePart(NBTTagCompound nbt) {
        nbt.setIntArray("pos", new int[]{x, y, z});
        return nbt;
    }

    @Override
    public int[] getCoords() {
        return new int[]{x, y, z};
    }
}
