package com.powerboat9.partsmod.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileAssembler extends TileEntity implements IInventory {
    public boolean canInteractWith(EntityPlayer p) {
        return p.getDistanceSqToCenter(this.pos) < 64;
    }

    public static final int SLOT_ASSEMBLED = 36;

    public ItemStack[] items;

    public TileAssembler() {
        items = new ItemStack[this.getSizeInventory()];
    }

    @Override
    public int getSizeInventory() {
        return 37;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < items.length; ++i) {
            if (items[i] != null) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index == SLOT_ASSEMBLED) {
            return
        }
        return ((index >= 0) && (index <= this.getSizeInventory())) ? items[index] : null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if (stack == null) {
            return null;
        } else if (stack.getCount() <= count) {
            setInventorySlotContents(index, null);
            markDirty();
            return stack;
        } else {
            ItemStack remove = stack.splitStack(count);
            markDirty();
            return remove;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack i = getStackInSlot(index);
        setInventorySlotContents(index, null);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    String customName;

    @Override
    public String getName() {
        return customName == null ? "container.assembler" : customName;
    }

    @Override
    public boolean hasCustomName() {
        return customName != null;
    }

    public void setCustomName(String name) {
        customName = name;
    }


}