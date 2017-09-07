package com.pengu.lostthaumaturgy.intr.jei.fuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import com.pengu.hammercore.common.InterItemStack;
import com.pengu.lostthaumaturgy.api.fuser.iFuserRecipe;
import com.pengu.lostthaumaturgy.core.Info.JEIConstans;

public class FuserRecipeHandler implements IRecipeHandler<iFuserRecipe>
{
	@Override
	public String getRecipeCategoryUid(iFuserRecipe recipe)
	{
		return JEIConstans.FUSER;
	}
	
	@Override
	public Class<iFuserRecipe> getRecipeClass()
	{
		return iFuserRecipe.class;
	}
	
	@Override
	public IRecipeWrapper getRecipeWrapper(iFuserRecipe recipe)
	{
		return new FuserRecipeWrapper(recipe);
	}
	
	@Override
	public boolean isRecipeValid(iFuserRecipe recipe)
	{
		return !InterItemStack.isStackNull(recipe.getOutput());
	}
}