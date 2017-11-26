package com.powerboat9.partsmod.craft;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PartRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private ItemStack result;
    private NonNullList<Ingredient> ingredients;

    public PartRecipe(NonNullList<Ingredient> ingredientsIn, ItemStack resultIn) {
        ingredients = ingredientsIn;
        result = resultIn;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean isHidden() {
        return true;
    }
}
