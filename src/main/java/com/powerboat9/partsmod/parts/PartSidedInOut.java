package com.powerboat9.partsmod.parts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class PartSidedInOut extends Part implements IPartPorts {
    IPartHolder holder;
    EnumFacing face;

    public PartSidedInOut(IPartHolder partHolder) {
        holder = partHolder;
    }

    @Override
    public void loadPart(NBTTagCompound nbt) {
        face = EnumFacing.VALUES[nbt.getInteger("direction")];
    }

    @Override
    public NBTTagCompound savePart(NBTTagCompound nbt) {
        nbt.setInteger("direction", face.getIndex());
        return nbt;
    }

    @Override
    public String modelVarient() {
        return face.getName();
    }
}