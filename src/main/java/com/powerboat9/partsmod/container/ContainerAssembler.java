package com.powerboat9.partsmod.container;

import com.powerboat9.partsmod.tiles.TileAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerAssembler extends Container {
    private TileAssembler t;

    public ContainerAssembler(TileAssembler t) {
        this.t = t;
        this.addSlotToContainer(new Slot(t, 0, 63, 17));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return t.canInteractWith(playerIn);
    }
}
