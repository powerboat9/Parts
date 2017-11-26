package com.powerboat9.partsmod.parts.input;

import com.powerboat9.partsmod.parts.IPartHolder;
import com.powerboat9.partsmod.parts.Part;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PartGrinder extends Part {
    public PartGrinder(IPartHolder h) {
        super(h);
    }

    @Override
    public void update(World worldIn) {
        BlockPos b = new BlockPos(holder.getHolderPos()).offset(face);
        IBlockState state = worldIn.getBlockState(b);
        state.getBlock().dropBlockAsItem(worldIn, b, state, 0);
        state.getBlock().breakBlock(worldIn, b, state);
        worldIn.setBlockToAir(b);
    }

    @Override
    public boolean takesUp(int x, int y, int z) {
        return false;
    }

    @Override
    public String[] dependants() {
        return new String[0];
    }
}
