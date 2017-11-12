package com.powerboat9.partsmod.blocks;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.network.PartsGuiHandler;
import com.powerboat9.partsmod.tiles.TileResearcher;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockResearcher extends BlockBase implements ITileEntityProvider {
    public BlockResearcher() {
        super(Material.IRON, "blockResearcher");
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.isBlockContainer = true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileResearcher();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        if (worldIn.getTileEntity(pos) != null) {
            worldIn.removeTileEntity(pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity t = worldIn.getTileEntity(pos);
        return t == null ? false : t.receiveClientEvent(id, param);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            playerIn.openGui(PartsModMain.instance, PartsGuiHandler.GUI_RESEARCHER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}