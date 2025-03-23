package com.iafenvoy.iceandfire.compat.jei;

import com.iafenvoy.iceandfire.registry.IafBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.item.ItemStack;

//By jdkdigital
public class FireDragonForgeRecipeCategory extends DragonForgeRecipeCategory {
    public FireDragonForgeRecipeCategory(IGuiHelper guiHelper) {
        super(
                guiHelper,
                IceAndFireJeiPlugin.FIRE,
                "fire",
                guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(IafBlocks.DRAGONFORGE_FIRE_CORE.get()))
        );
    }
}
