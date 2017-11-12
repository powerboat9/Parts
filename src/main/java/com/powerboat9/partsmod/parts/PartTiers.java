package com.powerboat9.partsmod.parts;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class PartTiers extends WorldSavedData {
    public static HashMap<UUID, ArrayList<String>> achieve;

    public PartTiers() {
        super("partsmod-achievements");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList players = nbt.getTagList("players", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < players.tagCount(); ++i) {
            NBTTagCompound player = players.getCompoundTagAt(i);
            if (player != null) {
                UUID playerUUID = player.getUniqueId("uuid");
                if (!achieve.containsKey(playerUUID) && (playerUUID != null)) {
                    ArrayList<String> has = new ArrayList<>();
                    NBTTagList nbtHas = player.getTagList("has", Constants.NBT.TAG_STRING);
                    for (int j = 0; j < nbtHas.tagCount(); ++i) {
                        NBTBase s = nbtHas.get(j);
                        if (s != null) {
                            has.add(s.toString());
                        }
                    }
                    achieve.put(playerUUID, has);
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList players = new NBTTagList();
        achieve.forEach((uuid, strings) -> {
            NBTTagCompound player = new NBTTagCompound();
            player.setUniqueId("uuid", uuid);
            NBTTagList has = new NBTTagList();
            for (int i = 0; i < strings.size(); i++) {
                has.appendTag(new NBTTagString(strings.get(i)));
            }
            player.setTag("has", has);
            players.appendTag(player);
        });
        compound.setTag("players", players);
        return compound;
    }
}