package com.pengu.lostthaumaturgy.intr.hc;

import com.pengu.hammercore.recipeAPI.RecipePlugin;
import com.pengu.hammercore.recipeAPI.iRecipePlugin;
import com.pengu.hammercore.recipeAPI.iRecipeTypeRegistry;
import com.pengu.lostthaumaturgy.intr.hc.rts.RecipeTypeCrucible;
import com.pengu.lostthaumaturgy.intr.hc.rts.RecipeTypeInfuser;

@RecipePlugin
public class LTHCRecipes implements iRecipePlugin
{
	@Override
	public void registerTypes(iRecipeTypeRegistry reg)
	{
		reg.register(new RecipeTypeCrucible());
		reg.register(new RecipeTypeInfuser());
	}
}