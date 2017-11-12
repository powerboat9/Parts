package com.powerboat9.partsmod.parts.input;

import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.PartSidedInOut;
import net.minecraft.util.EnumFacing;

public class PartPump extends PartSidedInOut {
    public PartPump(IPartHolder partHolder) {
        super(partHolder);
    }

    @Override
    public boolean doesAccept(String resource, int x, int y, int z, EnumFacing f) {
        return false;
    }

    @Override
    public String[] accepts(int x, int y, int z, EnumFacing f) {
        return new String[0];
    }

    @Override
    public boolean doesOutput(String resource, int x, int y, int z, EnumFacing f) {
        return true;
    }

    @Override
    public String[] outputs(int x, int y, int z, EnumFacing f) {
        return new String[]{"ALL"};
    }

    @Override
    public int[] getCoords() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean takesUp(int x, int y, int z) {
        return false;
    }

    @Override
    public EnumFacing orientation() {
        return null;
    }

    @Override
    public String[] dependants() {
        return new String[0];
    }
}
