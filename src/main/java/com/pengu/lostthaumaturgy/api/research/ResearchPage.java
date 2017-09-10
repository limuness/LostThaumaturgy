package com.pengu.lostthaumaturgy.api.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.pengu.lostthaumaturgy.api.RecipesInfuser;
import com.pengu.lostthaumaturgy.api.RecipesInfuser.DarkInfuserRecipe;
import com.pengu.lostthaumaturgy.api.RecipesInfuser.InfuserRecipe;
import com.pengu.lostthaumaturgy.api.fuser.RecipesFuser;
import com.pengu.lostthaumaturgy.api.fuser.iFuserRecipe;
import com.pengu.lostthaumaturgy.api.research.client.iRenderExtension;

public class ResearchPage
{
	public ResearchPage.PageType type = ResearchPage.PageType.TEXT;
	public String text;
	public String research;
	public ResourceLocation image;
	private Object recipe;
	public ItemStack recipeOutput;
	
	public static List<IRecipe> findRecipesFor(ItemStack out)
	{
		List<IRecipe> recipes = new ArrayList<>();
		
		CraftingManager.REGISTRY.forEach(recipe ->
		{
			if(recipe.getRecipeOutput().isItemEqual(out))
				recipes.add(recipe);
		});
		
		return recipes;
	}
	
	public Object getRecipe()
	{
		if(type == PageType.NORMAL_CRAFTING)
			if(recipe instanceof ItemStack[])
			{
				List<IRecipe> rs = new ArrayList<>();
				for(ItemStack r : (ItemStack[]) recipe)
					rs.addAll(findRecipesFor(r));
				return rs.toArray(new IRecipe[0]);
			} else if(recipe instanceof ItemStack)
				return findRecipesFor((ItemStack) recipe).toArray(new IRecipe[0]);
		
		if(type == PageType.INFUSION_CRAFTING)
			if(recipe == null || (recipe instanceof Object[] && ((Object[]) recipe).length == 0))
				recipe = InfuserRecipe.asRecipes(RecipesInfuser.listRecipes().findRecipes(recipeOutput, false));
		
		if(type == PageType.DARK_INFUSION_CRAFTING)
			if(recipe == null || (recipe instanceof Object[] && ((Object[]) recipe).length == 0))
				recipe = InfuserRecipe.asRecipes(RecipesInfuser.listRecipes().findRecipes(recipeOutput, true));
		
		if(type == PageType.FUSER_CRAFTING)
			if(recipe == null)
				recipe = RecipesFuser.getInstance().findRecipes(recipeOutput);
		
		return recipe;
	}
	
	/** For adding custom recipe support */
	public ResearchPage(Object recipe, PageType page)
    {
		type = page;
		this.recipe = recipe;
    }
	
	public ResearchPage(String text)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.TEXT;
		this.text = text;
	}
	
	public ResearchPage(String research, String text)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.TEXT_CONCEALED;
		this.research = research;
		this.text = text;
	}
	
	public ResearchPage(iFuserRecipe[] recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.FUSER_CRAFTING;
		this.recipe = recipe;
	}
	
	public ResearchPage(InfuserRecipe[] recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.INFUSION_CRAFTING;
		this.recipe = recipe;
	}
	
	public ResearchPage(List recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.COMPOUND_CRAFTING;
		this.recipe = recipe;
	}
	
	public ResearchPage(iFuserRecipe recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.FUSER_CRAFTING;
		this.recipe = recipe;
		this.recipeOutput = recipe.getOutput();
	}
	
	public ResearchPage(ItemStack input)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.SMELTING;
		this.recipe = input;
		this.recipeOutput = FurnaceRecipes.instance().getSmeltingResult(input).copy();
	}
	
	public ResearchPage(InfuserRecipe recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.INFUSION_CRAFTING;
		this.recipe = recipe;
		this.recipeOutput = recipe.result.copy();
	}
	
	public ResearchPage(DarkInfuserRecipe recipe)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipeOutput = recipe.result.copy();
		this.type = ResearchPage.PageType.DARK_INFUSION_CRAFTING;
		this.recipe = recipe;
	}
	
	public ResearchPage(ResourceLocation image, String caption)
	{
		this.text = null;
		this.research = null;
		this.image = null;
		this.recipe = null;
		this.recipeOutput = null;
		this.type = ResearchPage.PageType.IMAGE;
		this.image = image;
		this.text = caption;
	}
	
	public ResearchPage(PageType type, ItemStack item)
	{
		if(type == PageType.NORMAL_CRAFTING)
		{
			this.text = null;
			this.research = null;
			this.image = null;
			this.type = type;
			this.recipe = item;
			this.recipeOutput = item;
		} else if(type == PageType.INFUSION_CRAFTING)
		{
			this.text = null;
			this.research = null;
			this.image = null;
			this.recipeOutput = null;
			this.type = type;
			this.recipe = null;
			this.recipeOutput = item;
		} else if(type == PageType.DARK_INFUSION_CRAFTING)
		{
			this.text = null;
			this.research = null;
			this.image = null;
			this.recipeOutput = null;
			this.type = type;
			this.recipe = null;
			this.recipeOutput = item;
		} else if(type == PageType.SMELTING)
		{
			this.text = null;
			this.research = null;
			this.image = null;
			this.recipe = null;
			this.recipeOutput = null;
			this.type = type;
			this.recipe = item;
			this.recipeOutput = FurnaceRecipes.instance().getSmeltingResult(item).copy();
		} else if(type == PageType.FUSER_CRAFTING)
		{
			this.text = null;
			this.research = null;
			this.image = null;
			this.recipeOutput = null;
			this.type = type;
			this.recipe = null;
			this.recipeOutput = item;
		}
	}
	
	public String getTranslatedText()
	{
		String ret = "";
		
		if(this.text != null)
		{
			ret = I18n.format(this.text);
			if(ret.isEmpty())
				ret = this.text;
		}
		
		return ret;
	}
	
	public static class PageType
	{
		public static final PageType //
		        TEXT = new PageType("TEXT"), //
		        TEXT_CONCEALED = new PageType("TEXT_CONCEALED"), //
		        IMAGE = new PageType("IMAGE"), //
		        FUSER_CRAFTING = new PageType("ARCANE_CRAFTING"), //
		        NORMAL_CRAFTING = new PageType("NORMAL_CRAFTING"), //
		        INFUSION_CRAFTING = new PageType("INFUSION_CRAFTING"), //
		        COMPOUND_CRAFTING = new PageType("COMPOUND_CRAFTING"), //
		        DARK_INFUSION_CRAFTING = new PageType("INFUSION_ENCHANTMENT"), //
		        SMELTING = new PageType("SMELTING");
		
		private final String v1;
		private Object render;
		private final String renderClass;
		
		public PageType(String var1)
		{
			this(var1, null);
		}
		
		public PageType(String var1, String renderClass)
		{
			v1 = var1;
			this.renderClass = renderClass;
		}
		
		public String getV1()
		{
			return v1;
		}
		
		@SideOnly(Side.CLIENT)
		public iRenderExtension getRender()
		{
			if(renderClass == null || renderClass.isEmpty())
				return null;
			
			if(render == null)
			{
				try
				{
					render = (iRenderExtension) Class.forName(renderClass).newInstance();
				} catch(Throwable err)
				{
					System.err.println("Unable to construct iRenderExtension");
					err.printStackTrace();
				}
			}
			
			return (iRenderExtension) render;
		}
	}
}