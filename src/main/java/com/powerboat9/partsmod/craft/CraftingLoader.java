package com.powerboat9.partsmod.craft;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.powerboat9.partsmod.PartsModMain;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PartsModMain.modid)
public class CraftingLoader {
    public static final ResourceLocation TYPE = new ResourceLocation(PartsModMain.modid, "partRecipe");

    public static void loadPartCrafting() {
        CraftingHelper.register(TYPE, (IRecipeFactory) (context, json) -> {
            NonNullList<Ingredient> ingredients = NonNullList.create();
            for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients")) {
                ingredients.add(CraftingHelper.getIngredient(ele, context));
            }
            if (ingredients.isEmpty()) throw new JsonSyntaxException("No ingredients specified");
            if (ingredients.size() > 36) throw new JsonSyntaxException("Too many (>36) ingredients specified");
            ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
            return new PartRecipe(ingredients, result);
        });
    }
}
