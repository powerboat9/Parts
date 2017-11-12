package com.powerboat9.partsmod.tiles;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.parts.Part;
import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.PartRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

public class TilePartCase extends TileEntity implements ITickable, IPartHolder {
    public ArrayList<Part> parts = new ArrayList<>();

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbtParts = compound.getTagList("parts", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < nbtParts.tagCount(); ++i) {
            NBTTagCompound partCompound = nbtParts.getCompoundTagAt(i);
            Part part = PartRegistry.createPart(this, partCompound.getString("id"));
            if (part != null) {
                part.loadPart(partCompound);
                parts.add(part);
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList nbtParts = new NBTTagList();
        compound.setTag("parts", nbtParts);
        for (int i = 1; i < parts.size(); ++i) {
            Part part = parts.get(i);
            NBTTagCompound partTag = part.savePart(new NBTTagCompound());
            partTag.setString("id", part.getRegistryName().toString());
            nbtParts.appendTag(partTag);
        }
        return compound;
    }

    @Override
    public void update() {
        for (Part part : parts) {
            part.update();
        }
    }

    @Override
    public Part getPart(int x, int y, int z) {
        for (Part part : parts) {
            if (part.takesUp(x, y, z)) {
                return part;
            }
        }
        return null;
    }
}