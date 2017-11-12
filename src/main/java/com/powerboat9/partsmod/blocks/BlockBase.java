package com.powerboat9.partsmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {
    public BlockBase(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }

    public ItemBlock getItemBlock() {
        return (ItemBlock) new ItemBlock(this).setRegistryName(this.getRegistryName());
    }
}
