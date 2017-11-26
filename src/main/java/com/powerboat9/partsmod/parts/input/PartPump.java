package com.powerboat9.partsmod.parts.input;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.IPartPorts;
import com.powerboat9.partsmod.parts.Part;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class PartPump extends Part implements IPartPorts {
    public PartPump(IPartHolder partHolder) {
        super(partHolder);
        setRegistryName(PartsModMain.modid, "pump");
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
    public void update(World worldIn) {
    }

    @Override
    public boolean takesUp(int x, int y, int z) {
        return false;
    }

    @Override
    public String[] dependants() {
        return new String[0];
    }
}
