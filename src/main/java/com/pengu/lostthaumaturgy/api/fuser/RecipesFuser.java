package com.pengu.lostthaumaturgy.api.fuser;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.pengu.lostthaumaturgy.LostThaumaturgy;
import com.pengu.lostthaumaturgy.api.fuser.recipes.ShapedFuserRecipe;
import com.pengu.lostthaumaturgy.api.fuser.recipes.ShapelessFuserRecipe;
import com.pengu.lostthaumaturgy.api.fuser.recipes.TheoryToDiscovery;
import com.pengu.lostthaumaturgy.core.items.ItemMultiMaterial.EnumMultiMaterialType;
import com.pengu.lostthaumaturgy.init.BlocksLT;
import com.pengu.lostthaumaturgy.init.ItemsLT;
import com.pengu.lostthaumaturgy.init.ResearchesLT;

public class RecipesFuser
{
	private static final RecipesFuser INSTANCE = new RecipesFuser();
	
	private final List<IFuserRecipe> recipes = Lists.<IFuserRecipe> newArrayList();
	
	/**
	 * Constructs a new instance with all vanilla recipes pre-registered.
	 */
	private RecipesFuser()
	{
		LostThaumaturgy.LOG.info("Registering Arcane Crafter Recipes...");
		
		addRecipe(new ShapedFuserRecipe(BlocksLT.VIS_PUMP, "waw", "wcw", "wbw", 'w', EnumMultiMaterialType.ENCHANTED_WOOD, 'a', EnumMultiMaterialType.AQUEOUS_CRYSTAL, 'b', BlocksLT.BELLOWS, 'c', BlocksLT.CONDUIT).setVisUsage(2F, 0F));
		addRecipe(new ShapedFuserRecipe(BlocksLT.VIS_FILTER, "wiw", "cac", "wiw", 'w', EnumMultiMaterialType.ENCHANTED_WOOD, 'i', "ingotIron", 'c', BlocksLT.CONDUIT, 'a', EnumMultiMaterialType.ALUMENTUM).setVisUsage(1.5F, 1.5F));
//		addRecipe(new ShapedFuserRecipe(ItemsLT.ELEMENTAL_PICKAXE, "fff", " t ", " w ", 'f', EnumMultiMaterialType.FIERY_CRYSTAL, 't', ItemsLT.THAUMIUM_PICKAXE, 'w', EnumMultiMaterialType.ENCHANTED_WOOD).setVisUsage(5, 0).setResearch(ResearchesLT.ELEMENTAL_PICKAXE));
//		addRecipe(new ShapedFuserRecipe(ItemsLT.ELEMENTAL_AXE, "ff", "ft", " w", 'f', EnumMultiMaterialType.AQUEOUS_CRYSTAL, 't', ItemsLT.THAUMIUM_AXE, 'w', EnumMultiMaterialType.ENCHANTED_WOOD).setVisUsage(10.5F, 0).setResearch(ResearchesLT.ELEMENTAL_AXE).setMirrored(true));
//		addRecipe(new ShapedFuserRecipe(ItemsLT.ELEMENTAL_SWORD, " v ", "vtv", " w ", 'v', EnumMultiMaterialType.VAPOROUS_CRYSTAL, 't', ItemsLT.THAUMIUM_SWORD, 'w', EnumMultiMaterialType.ENCHANTED_WOOD).setVisUsage(10.5F, 0).setResearch(ResearchesLT.ELEMENTAL_SWORD));
//		addRecipe(new ShapedFuserRecipe(ItemsLT.ELEMENTAL_SHOVEL, " v ", "vtv", " w ", 'v', EnumMultiMaterialType.EARTHEN_CRYSTAL, 't', ItemsLT.THAUMIUM_SHOVEL, 'w', EnumMultiMaterialType.ENCHANTED_WOOD).setVisUsage(10.5F, 0).setResearch(ResearchesLT.ELEMENTAL_SHOVEL));
		addRecipe(new ShapedFuserRecipe(ItemsLT.ELEMENTAL_HOE, "vv", " t", " w", 'v', EnumMultiMaterialType.VIS_CRYSTAL, 't', ItemsLT.THAUMIUM_HOE, 'w', EnumMultiMaterialType.ENCHANTED_WOOD).setMirrored(true).setVisUsage(10.5F, 0));
//		addRecipe(new ShapedFuserRecipe(BlocksLT.VIS_PURIFIER, "geg", "ses", "geg", 'g', "ingotGold", 'e', EnumMultiMaterialType.ENCHANTED_SILVERWOOD, 's', BlocksLT.SILVERWOOD_LOG).setResearch(ResearchesLT.VIS_PURIFIER).setVisUsage(1.25F, 3.5F));
		addRecipe(new ShapelessFuserRecipe(Items.BLAZE_POWDER, EnumMultiMaterialType.CINDERPEARL_POD));
//		addRecipe(new ShapedFuserRecipe(BlocksLT.CRYSTALLIZER, "123", "456", "787", '1', EnumMultiMaterialType.VAPOROUS_CRYSTAL, '2', EnumMultiMaterialType.VIS_CRYSTAL, '3', EnumMultiMaterialType.AQUEOUS_CRYSTAL, '4', EnumMultiMaterialType.EARTHEN_CRYSTAL, '5', "gemDiamond", '6', EnumMultiMaterialType.FIERY_CRYSTAL, '7', "ingotGold", '8', "ingotIron").setVisUsage(15F, 0).setResearch(ResearchesLT.CRYSTALLIZER));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.TRAVELING_TRUNK.stack(), "www", "wsw", "www", 'w', EnumMultiMaterialType.ENCHANTED_WOOD, 's', EnumMultiMaterialType.SOUL_FRAGMENT).setVisUsage(25F, 0));
		addRecipe(new ShapedFuserRecipe(BlocksLT.GENERATOR, "gwg", "wsw", "gwg", 'g', "paneGlass", 'w', EnumMultiMaterialType.ENCHANTED_WOOD.stack(), 's', ItemsLT.STABILIZED_SINGULARITY).setVisUsage(1.5F, 0));
//		addRecipe(new ShapedFuserRecipe(BlocksLT.ADVANCED_VIS_VALVE, " v ", "gcg", " t ", 'v', EnumMultiMaterialType.VIS_CRYSTAL, 't', EnumMultiMaterialType.TAINTED_CRYSTAL, 'g', "ingotGold", 'c', BlocksLT.VIS_VALVE).setResearch(ResearchesLT.ADVANCED_VIS_VALVE).setVisUsage(4.5F, 0));
		addRecipe(new ShapedFuserRecipe(BlocksLT.WAND_CONSTRUCTOR, "crc", "sss", " s ", 'c', EnumMultiMaterialType.CAP_IRON, 'r', Items.STICK, 's', BlocksLT.INFUSER_BASE));
		addRecipe(new ShapedFuserRecipe(BlocksLT.DARKNESS_GENERATOR, " s ", "igi", "bbb", 's', EnumMultiMaterialType.DARKNESS_SEED, 'i', "ingotVoid", 'g', EnumMultiMaterialType.ELDRITCH_MECHANISM, 'b', BlocksLT.ELDRITCH_BLOCK).setVisUsage(50.25F, 25.25F));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.ROD_GREATWOOD.stack(), " r", "r ", 'r', BlocksLT.GREATWOOD_LOG).setVisUsage(12F, 0));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.ROD_SILVERWOOD.stack(), " r", "r ", 'r', BlocksLT.SILVERWOOD_LOG).setVisUsage(55F, 25.5F));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.CAP_GOLD.stack(), "nnn", "n n", 'n', "nuggetGold"));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.CAP_THAUMIUM.stack(2), "nnn", "n n", 'n', "ingotThaumium").setVisUsage(5, 0));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.CAP_VOID.stack(1), "nnn", "n n", 'n', "ingotVoid").setVisUsage(15.5F, 0));
//		addRecipe(new ShapedFuserRecipe(BlocksLT.REPAIRER, "wiw", "wpw", "wiw", 'w', EnumMultiMaterialType.ENCHANTED_WOOD, 'i', "ingotIron", 'p', EnumMultiMaterialType.ANIMATED_PISTON).setVisUsage(20F, 0).setResearch(ResearchesLT.REPAIRER));
		addRecipe(new ShapedFuserRecipe(BlocksLT.DUPLICATOR, "gpg", "w w", "gpg", 'g', "ingotGold", 'p', EnumMultiMaterialType.ANIMATED_PISTON.stack(), 'w', EnumMultiMaterialType.ENCHANTED_WOOD.stack()).setVisUsage(40, 1));
		addRecipe(new ShapedFuserRecipe(BlocksLT.INFUSER_DARK, "oso", "iti", "ooo", 'o', "obsidian", 's', Blocks.STONE_SLAB, 'i', "ingotIron", 't', EnumMultiMaterialType.TAINTED_CRYSTAL.stack()).setVisUsage(10, 16));
		addRecipe(new ShapelessFuserRecipe(new ItemStack(BlocksLT.ELDRITCH_BLOCK, 4), EnumMultiMaterialType.DARKNESS_SEED.stack(), "stone", "stone", "stone", "stone").setVisUsage(4, 4));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.ELDRITCH_KEYSTONE_INERT.stack(), " g ", "g g", " g ", 'g', EnumMultiMaterialType.ELDRITCH_MECHANISM.stack()).setVisUsage(0, 8));
		addRecipe(new ShapedFuserRecipe(EnumMultiMaterialType.ELDRITCH_MECHANISM.stack(), " g ", "g g", " g ", 'g', "ingotVoid").setVisUsage(0, 6.66F));
		addRecipe(new TheoryToDiscovery());
		
		LostThaumaturgy.LOG.info("Registered " + recipes.size() + " Default Recipes.");
	}
	
	/**
	 * Adds a default shaped recipe for {@link ItemStack}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapedRecipe(ItemStack item, Object... recipe)
	{
		addRecipe(new ShapedFuserRecipe(item, recipe));
	}
	
	/**
	 * Adds a default shaped recipe for {@link Item}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapedRecipe(Item item, Object... recipe)
	{
		addRecipe(new ShapedFuserRecipe(item, recipe));
	}
	
	/**
	 * Adds a default shaped recipe for {@link Block}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapedRecipe(Block block, Object... recipe)
	{
		addRecipe(new ShapedFuserRecipe(block, recipe));
	}
	
	/**
	 * Adds a default shapeless recipe for {@link ItemStack}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapelessRecipe(ItemStack item, Object... recipe)
	{
		addRecipe(new ShapelessFuserRecipe(item, recipe));
	}
	
	/**
	 * Adds a default shapeless recipe for {@link Item}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapelessRecipe(Item item, Object... recipe)
	{
		addRecipe(new ShapelessFuserRecipe(item, recipe));
	}
	
	/**
	 * Adds a default shapeless recipe for {@link Block}. It uses default
	 * minecraft's recipe patterns and supports {@link OreDictionary} (string
	 * components)
	 */
	public void addShapelessRecipe(Block block, Object... recipe)
	{
		addRecipe(new ShapelessFuserRecipe(block, recipe));
	}
	
	/**
	 * Adds a custom recipe that can handle other factors like moon phase, etc.
	 */
	public void addRecipe(IFuserRecipe recipe)
	{
		if(Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
			throw new RuntimeException("Unable to register recipes after init phase! Please move your registration code to init!");
		recipes.add(recipe);
	}
	
	/**
	 * Retrieves a list with all recipes registered. You can remove any recipe
	 * if you feel like tweaking the gameplay.
	 */
	public List<IFuserRecipe> getRecipes()
	{
		return recipes;
	}
	
	/**
	 * Gets the current instance of recipe registry for Arcane Crafter.
	 */
	public static RecipesFuser getInstance()
	{
		return INSTANCE;
	}
}