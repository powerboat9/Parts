package com.powerboat9.partsmod.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TileResearcher extends TileEntity {
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && (playerIn.getDistanceSqToCenter(getPos()) < 64);
    }
}
