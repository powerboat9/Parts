package com.powerboat9.partsmod.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.Constants;

public class TileAssembler extends TileEntity implements IInventory {
    public boolean canInteractWith(EntityPlayer p) {
        return p.getDistanceSqToCenter(this.pos) < 64;
    }

    public static final int SLOT_ASSEMBLED = 36;

    public ItemStack[] items;

    public TileAssembler() {
        items = new ItemStack[this.getSizeInventory() - 1];
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
        if (index == SLOT_ASSEMBLED) return new ItemStack(Items.BED);
        return (((index >= 0) && (index < (36)) && (items[index] != null))) ? items[index] : ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (index == SLOT_ASSEMBLED) return ItemStack.EMPTY;
        ItemStack stack = getStackInSlot(index);
        if (stack == null) {
            return ItemStack.EMPTY;
        } else if (stack.getCount() <= count) {
            setInventorySlotContents(index, null); // markDirty handled in method
            // markDirty();
            return stack;
        } else {
            ItemStack remove = stack.splitStack(count);
            markDirty();
            return remove;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index == SLOT_ASSEMBLED) return ItemStack.EMPTY;
        ItemStack i = getStackInSlot(index);
        setInventorySlotContents(index, null);
        return i;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if ((index >= 0) && (index < 36)) {
            items[index] = stack;
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; ++i) {
            items[i] = null;
        }
        markDirty();
    }

    String customName = null;

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

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList nbtItems = new NBTTagList();
        for (int i = 0; i < (this.getSizeInventory() - 1); ++i) {
            if ((items[i] != null) && (!items[i].isEmpty())) {
                NBTTagCompound nbtItem = new NBTTagCompound();
                nbtItem.setByte("Slot", (byte) i);
                items[i].writeToNBT(nbtItem);
                nbtItems.appendTag(nbtItem);
            }
        }
        compound.setTag("Items", nbtItems);
        if (customName != null) compound.setString("CustomName", customName);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbtItems = new NBTTagList();
        for (int i = 0; i < nbtItems.tagCount(); ++i) {
            NBTBase nbtItemBase = nbtItems.get(i);
            if (nbtItemBase instanceof NBTTagCompound) {
                NBTTagCompound nbtItem = (NBTTagCompound) nbtItemBase;
                int slot = nbtItem.getByte("Slot");
                if ((slot >= 0) && (slot < (getSizeInventory() - 1))) {
                    items[slot] = new ItemStack(nbtItem);
                }
            }
        }
        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) customName = compound.getString("CustomName");
    }
}