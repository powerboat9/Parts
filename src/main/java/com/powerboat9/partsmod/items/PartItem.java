package com.powerboat9.partsmod.items;

import com.powerboat9.partsmod.parts.Part;
import net.minecraft.item.Item;

public class PartItem extends Item {
    private Part part;

    public PartItem(Part p) {
        super();
        if (p == null) {
            this.setUnlocalizedName("partItem[null]");
            this.setRegistryName("partItem[null]");
        } else {
            this.setUnlocalizedName("partItem." + p.getRegistryName().toString());
            this.setRegistryName("partItem." + p.getRegistryName());
            this.bFull3D = true;
        }
        part = p;
    }

    public Part getPart() {
        return part;
    }
}
