package com.powerboat9.partsmod.network;

import com.powerboat9.partsmod.container.ContainerAssembler;
import com.powerboat9.partsmod.gui.GuiAssembler;
import com.powerboat9.partsmod.gui.GuiResearcher;
import com.powerboat9.partsmod.tiles.TileAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class PartsGuiHandler implements IGuiHandler {
    public static final int GUI_RESEARCHER = 0;
    public static final int GUI_ASSEMBLER = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ASSEMBLER:
                TileEntity t = world.getTileEntity(new BlockPos(x, y, z));
                if (t instanceof TileAssembler) {
                    return new ContainerAssembler((TileAssembler) t);
                }
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ASSEMBLER:
                TileEntity t = world.getTileEntity(new BlockPos(x, y, z));
                if (t instanceof TileAssembler) {
                    return new GuiAssembler(new ContainerAssembler((TileAssembler) t));
                }
                break;
        }
        return null;
    }
}
