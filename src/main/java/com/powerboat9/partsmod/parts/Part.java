package com.powerboat9.partsmod.parts;

import jline.internal.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.InvocationTargetException;

public abstract class Part extends IForgeRegistryEntry.Impl<Part> {
    public abstract int[] getCoords();

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

    public void render() {
    }

    // Whether to call this.render() during render phase, can be used with this.isSimple()
    public boolean isDynamic() {
        return false;
    }

    // Whether to use model during render phase, can be used with this.isDynamic()
    public boolean isSimple() {
        return true;
    }

    @Nullable
    public abstract String modelVarient();

    public abstract void loadPart(NBTTagCompound nbt);

    public abstract NBTTagCompound savePart(NBTTagCompound nbt);

    public abstract void update();

    public abstract boolean takesUp(int x, int y, int z);

    public abstract EnumFacing orientation();

    public abstract String[] dependants();
}