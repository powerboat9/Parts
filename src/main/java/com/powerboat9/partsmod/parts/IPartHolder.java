package com.powerboat9.partsmod.parts;

import net.minecraft.util.math.Vec3d;

public interface IPartHolder {
    Part getPart(int x, int y, int z);

    Vec3d getAngle(); /* counterclockwise angle rotation looking down the _ axis (x, y, z) */

    Vec3d getHolderPos();
}
