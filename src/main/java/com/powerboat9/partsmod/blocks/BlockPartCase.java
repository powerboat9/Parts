package com.powerboat9.partsmod.blocks;

import com.powerboat9.partsmod.tiles.TilePartCase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPartCase extends BlockBase implements ITileEntityProvider {
    public BlockPartCase() {
        super(Material.IRON, "blockPartCase");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TilePartCase();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity e = worldIn.getTileEntity(pos);
        return e == null ? false : e.receiveClientEvent(id, param);
    }
}
