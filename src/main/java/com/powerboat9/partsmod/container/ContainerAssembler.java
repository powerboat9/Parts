package com.powerboat9.partsmod.container;

import com.powerboat9.partsmod.tiles.TileAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAssembler extends Container {
    private TileAssembler t;

    public ContainerAssembler(TileAssembler t, IInventory playerInventory) {
        this.t = t;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(t, x + y * 9, 8 + x * 18, 32 + y * 18));

                // Copied from ContainerFurnace
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 98 + y * 18));

                if (y == 0) {
                    this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 156));
                }
            }
        }
        this.addSlotToContainer(new Slot(t, TileAssembler.SLOT_ASSEMBLED, 80, 8) {
            @Override
            public boolean canTakeStack(EntityPlayer playerIn) {
                return false;
            }
        });
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return t.canInteractWith(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot fromSlot = this.inventorySlots.get(index);
        ItemStack dupe = null;
        if ((fromSlot != null) && fromSlot.getHasStack()) {
            ItemStack fromStack = fromSlot.getStack();
            dupe = fromStack.copy();
            if (fromSlot.slotNumber < TileAssembler.SLOT_ASSEMBLED) {
                if (!mergeItemStack(fromStack, TileAssembler.SLOT_ASSEMBLED + 1, 63, true)) return null;
            } else {
                if (!mergeItemStack(fromStack, 0, TileAssembler.SLOT_ASSEMBLED - 1, false)) return null;
            }
            if (fromStack.getCount() == 0) {
                fromSlot.putStack(ItemStack.EMPTY.copy());
            } else {
                fromSlot.onSlotChanged();
            }
            if (dupe.getCount() == fromStack.getCount()) return null;
            fromSlot.onTake(playerIn, fromStack);
        }
        return dupe;
    }
}
