package com.powerboat9.partsmod.parts;

import jline.internal.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.InvocationTargetException;

public abstract class Part extends IForgeRegistryEntry.Impl<Part> {
    public IPartHolder holder;
    public EnumFacing face = EnumFacing.NORTH;
    int x = 0, y = 0, z = 0;

    public Part(IPartHolder partHolder) {
        holder = partHolder;
    }

    public Part createNew(IPartHolder partHolder) {
        try {
            return this.getClass().getDeclaredConstructor(IPartHolder.class).newInstance(partHolder);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] getCoords() {
        return new int[]{x, y, z};
    }

    public EnumFacing getRot() {
        return face;
    }

    public void render() {
    }

    public Vec3d getModelRot() {
        switch (face) {
            case DOWN: return new Vec3d(90, 0, 0);
            case UP: return new Vec3d(-90, 0, 0);
            case SOUTH: return new Vec3d(0, 180, 0);
            case WEST: return new Vec3d(0, -90, 0);
            case EAST: return new Vec3d(0, 90, 0);
            default: return Vec3d.ZERO;
        }
    }

    // Whether to call this.render() during render phase, can be used with this.isSimple()
    public boolean isDynamic() {
        return false;
    }

    // Whether to use model during render phase, can be used with this.isDynamic()
    public boolean isSimple() {
        return true;
    }

    public String modelVariant(@Nullable ItemStack itemStack) {
        return "normal";
    }

    public void loadPart(NBTTagCompound nbt) {
        int[] loc = nbt.getIntArray("pos");
        x = loc[0];
        y = loc[1];
        z = loc[2];
        face = EnumFacing.VALUES[nbt.getInteger("direction")];
    }

    public NBTTagCompound savePart(NBTTagCompound nbt) {
        nbt.setIntArray("pos", new int[]{x, y, z});
        nbt.setInteger("direction", face.getIndex());
        return nbt;
    }

    public abstract void update(World worldIn);

    public abstract boolean takesUp(int x, int y, int z);

    public abstract String[] dependants();
}