package com.powerboat9.partsmod.blocks;

import com.powerboat9.partsmod.PartsModMain;
import com.powerboat9.partsmod.network.PartsGuiHandler;
import com.powerboat9.partsmod.tiles.TileAssembler;
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

public class BlockAssembler extends BlockBase implements ITileEntityProvider {
    public BlockAssembler() {
        super(Material.IRON, "blockAssembler");
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(PartsModMain.instance, PartsGuiHandler.GUI_ASSEMBLER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAssembler();
    }
}
