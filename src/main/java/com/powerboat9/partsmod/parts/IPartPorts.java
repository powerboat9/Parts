package com.powerboat9.partsmod.parts;

import net.minecraft.util.EnumFacing;

public interface IPartPorts {
    default boolean doesAccept(String resource, int x, int y, int z, EnumFacing f) {
        for (String res : this.accepts(x, y, z, f)) {
            if (res.equals(resource)) {
                return true;
            }
        }
        return false;
    }

    String[] accepts(int x, int y, int z, EnumFacing f);

    default boolean doesOutput(String resource, int x, int y, int z, EnumFacing f) {
        for (String res : this.outputs(x, y, z, f)) {
            if (res.equals(resource)) {
                return true;
            }
        }
        return false;
    }

    String[] outputs(int x, int y, int z, EnumFacing f);
}