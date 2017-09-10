package com.pengu.lostthaumaturgy.init;

import java.util.Arrays;

import com.pengu.lostthaumaturgy.api.research.ResearchCategories;
import com.pengu.lostthaumaturgy.api.research.ResearchCategoryList;
import com.pengu.lostthaumaturgy.api.research.ResearchItem;
import com.pengu.lostthaumaturgy.api.research.ResearchManager;
import com.pengu.lostthaumaturgy.api.research.ResearchPage;
import com.pengu.lostthaumaturgy.api.research.ResearchSystem;
import com.pengu.lostthaumaturgy.core.Info;
import com.pengu.lostthaumaturgy.core.items.ItemMultiMaterial.EnumMultiMaterialType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ResearchesLT
{
	public static void registerResearches()
	{
		ResearchCategories.registerCategory("basics", new ResourceLocation(Info.MOD_ID, "textures/gui/book/basics_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/basics_back.png"));
		ResearchCategories.registerCategory("lost_knowledge", new ResourceLocation(Info.MOD_ID, "textures/gui/book/lost_knowledge_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/lost_knowledge_back.png"));
		ResearchCategories.registerCategory("thaumaturgy", new ResourceLocation(Info.MOD_ID, "textures/gui/book/thaumaturgy_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/thaumaturgy_back.png"));
		ResearchCategories.registerCategory("tainted", new ResourceLocation(Info.MOD_ID, "textures/gui/book/tainted_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/tainted_back.png"));
		ResearchCategories.registerCategory("forbidden", new ResourceLocation(Info.MOD_ID, "textures/gui/book/forbidden_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/forbidden_back.png"));
		
		ResearchCategories.registerCategory("eldritch", new ResearchCategoryList(new ResourceLocation(Info.MOD_ID, "textures/gui/book/eldritch_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/eldritch_back.png"))
		{
			@Override
			public boolean isVisibleTo(EntityPlayer player)
			{
				return ResearchSystem.isResearchCompleted(player, "darkness_seed");
			}
		});
		
		ResearchCategories.registerCategory("primordial", new ResearchCategoryList(new ResourceLocation(Info.MOD_ID, "textures/gui/book/primordial_icon.png"), new ResourceLocation(Info.MOD_ID, "textures/gui/book/primordial_back.png"))
		{
			@Override
			public boolean isVisibleTo(EntityPlayer player)
			{
				return true;
			}
		});
		
		new ResearchItem("atmosphere", "basics", 0, -1, 0, new ResourceLocation(Info.MOD_ID, "textures/gui/book/r_aura.png")).setAutoUnlock().setRound().setPages(new ResearchPage("lt.research_desc.atmosphere")).registerResearch();
		new ResearchItem("researching", "basics", -1, -1, 0, new ResourceLocation(Info.MOD_ID, "textures/gui/book/r_research.png")).setAutoUnlock().setRound().setPages(new ResearchPage("lt.research_desc.researching.1"), new ResearchPage("lt.research_desc.researching.2"), new ResearchPage(new ItemStack(BlocksLT.STUDIUM_TABLE), true), new ResearchPage(new ItemStack(BlocksLT.AUXILIUM_TABLE), true)).registerResearch();
		// new ResearchItem("minerals", "basics", -2, -1, 0, new
		// ItemStack(BlocksLT.CINNABAR_ORE)).setAutoUnlock().setRound().setPages(new
		// ResearchPage("lt.research_desc.minerals")).registerResearch();
		new ResearchItem("crucible", "basics", 1, 1, 0, new ItemStack(BlocksLT.CRUCIBLE)).setPages(new ResearchPage("lt.research_desc.crucible"), new ResearchPage(new ItemStack(BlocksLT.CRUCIBLE), true)).setAutoUnlock().setSpecial().registerResearch();
		new ResearchItem("vis_conduit", "basics", 3, 0, 0, new ItemStack(BlocksLT.CONDUIT)).setPages(new ResearchPage("lt.research_desc.vis_conduit"), new ResearchPage(new ItemStack(BlocksLT.CONDUIT), true)).setParents("crucible").setAutoUnlock().setRound().registerResearch();
		new ResearchItem("vis_tank", "basics", 2, -1, 0, new ItemStack(BlocksLT.VIS_TANK)).setPages(new ResearchPage("lt.research_desc.vis_tank"), new ResearchPage(new ItemStack(BlocksLT.VIS_TANK), true)).setParents("vis_conduit").setAutoUnlock().setRound().registerResearch();
		new ResearchItem("arc_crafter", "basics", 2, 3, 0, new ItemStack(BlocksLT.FUSER_MB)).setPages(new ResearchPage("lt.research_desc.arc_crafter"), new ResearchPage(Arrays.asList(true, 2, 1, 2, Arrays.asList(new ItemStack(BlocksLT.INFUSER_BASE), new ItemStack(BlocksLT.INFUSER_BASE), new ItemStack(BlocksLT.INFUSER_BASE), new ItemStack(BlocksLT.INFUSER_BASE))))).setParents("infuser").setAutoUnlock().setRound().registerResearch();
		
		new ResearchItem("crystalline_bell", "basics", 1, 3, 75, new ItemStack(ItemsLT.CRYSTALLINE_BELL)).setColor(0xAFFFFF).setPages(new ResearchPage("lt.research_desc.crystalline_bell"), new ResearchPage(null, new ItemStack(ItemsLT.CRYSTALLINE_BELL))).registerResearch();
		
		new ResearchItem("singularity", "thaumaturgy", 1, 3, 35, new ItemStack(ItemsLT.SINGULARITY)).setPages(new ResearchPage("lt.research_desc.singularity"), new ResearchPage(null, new ItemStack(ItemsLT.SINGULARITY))).registerResearch();
		new ResearchItem("wand_reversal", "thaumaturgy", 3, 3, 45, new ItemStack(ItemsLT.WAND_REVERSAL)).setColor(0x7298B3).setParents("singularity").setPages(new ResearchPage("lt.research_desc.wand_reversal"), new ResearchPage(null, new ItemStack(ItemsLT.WAND_REVERSAL))).registerResearch();
		new ResearchItem("quicksilver_core", "thaumaturgy", -1, -2, 35, new ItemStack(ItemsLT.QUICKSILVER_CORE)).setColor(0xBEBDEA).setPages(new ResearchPage("lt.research_desc.quicksilver_core"), new ResearchPage(null, new ItemStack(ItemsLT.QUICKSILVER_CORE))).registerResearch();
		// new ResearchItem("collected_wisdom", "thaumaturgy", 0, -2, 35, new
		// ItemStack(ItemsLT.COLLECTED_WISDOM)).setPages(new
		// ResearchPage("lt.research_desc.collected_wisdom"), new
		// ResearchPage(null, new
		// ItemStack(ItemsLT.COLLECTED_WISDOM))).registerResearch();
		new ResearchItem("stabilized_singularity", "thaumaturgy", 1, -2, 35, new ItemStack(ItemsLT.STABILIZED_SINGULARITY)).setParents("singularity").setPages(new ResearchPage("lt.research_desc.stabilized_singularity"), new ResearchPage(null, new ItemStack(ItemsLT.STABILIZED_SINGULARITY))).registerResearch();
		new ResearchItem("concetrated_purity", "thaumaturgy", 2, -2, 35, new ItemStack(ItemsLT.CONCENTRATED_PURITY)).setPages(new ResearchPage("lt.research_desc.concentrated_purity"), new ResearchPage(null, new ItemStack(ItemsLT.CONCENTRATED_PURITY))).registerResearch();
		new ResearchItem("crucible_eyes", "thaumaturgy", -5, 4, 65, new ItemStack(BlocksLT.CRUCIBLE_EYES)).setPages(new ResearchPage("lt.research_desc.crucible_eyes"), new ResearchPage(null, new ItemStack(BlocksLT.CRUCIBLE_EYES))).registerResearch();
		new ResearchItem("crucible_thaumium", "thaumaturgy", -5, 5, 66.6F, new ItemStack(BlocksLT.CRUCIBLE_THAUMIUM)).setParents("crucible_eyes").setPages(new ResearchPage("lt.research_desc.crucible_thaumium"), new ResearchPage(null, new ItemStack(BlocksLT.CRUCIBLE_THAUMIUM))).registerResearch();
		
		new ResearchItem("totem_twilight", "tainted", 1, 3, 40, new ItemStack(BlocksLT.TOTEM_TWILIGHT)).setPages(new ResearchPage("lt.research_desc.totem_twilight"), new ResearchPage(null, new ItemStack(BlocksLT.TOTEM_TWILIGHT))).registerResearch();
		new ResearchItem("totem_dawn", "tainted", 3, 3, 40, new ItemStack(BlocksLT.TOTEM_DAWN)).setPages(new ResearchPage("lt.research_desc.totem_dawn"), new ResearchPage(null, new ItemStack(BlocksLT.TOTEM_DAWN))).registerResearch();
		new ResearchItem("concentrated_taint", "tainted", 1, 1, 40, new ItemStack(ItemsLT.CUSTOM_POTION, 1, 1)).setPages(new ResearchPage("lt.research_desc.concentrated_taint"), new ResearchPage(null, new ItemStack(ItemsLT.CUSTOM_POTION, 1, 1))).registerResearch();
		
		new ResearchItem("darkness_seed", "eldritch", 0, 0, 50, EnumMultiMaterialType.DARKNESS_SEED.stack())
		{
			@Override
			public boolean canObtainFrom(ItemStack baseStack, EntityPlayer initiator)
			{
				return EnumMultiMaterialType.GLOWING_ELDRITCH_DEVICE.isThisItem(baseStack) || EnumMultiMaterialType.ELDRITCH_REPOSITORY.isThisItem(baseStack);
			}
		}.setSpecial().setPages(new ResearchPage("lt.research_desc.darkness_seed.1"), new ResearchPage("lt.research_desc.darkness_seed.2")).registerResearch();
		new ResearchItem("void_ingot", "eldritch", -2, -1, 40, EnumMultiMaterialType.VOID_INGOT.stack()).setPages(new ResearchPage("lt.research_desc.void_ingot"), new ResearchPage(null, EnumMultiMaterialType.VOID_INGOT.stack()), new ResearchPage(EnumMultiMaterialType.ELDRITCH_MECHANISM.stack(), true)).setParents("darkness_seed").registerResearch();
		new ResearchItem("crucible_void", "eldritch", -3, 0, 70, new ItemStack(BlocksLT.CRUCIBLE_VOID)).setParents("crucible_thaumium", "void_ingot").setHidden().setPages(new ResearchPage("lt.research_desc.crucible_void"), new ResearchPage(null, new ItemStack(BlocksLT.CRUCIBLE_VOID))).registerResearch();
		new ResearchItem("eldritch_keystone", "eldritch", -3, -2, 75, EnumMultiMaterialType.ELDRITCH_KEYSTONE_INERT.stack()).setPages(new ResearchPage("lt.research_desc.eldritch_keystone"), new ResearchPage(EnumMultiMaterialType.ELDRITCH_KEYSTONE_INERT.stack(), true), new ResearchPage(null, EnumMultiMaterialType.ELDRITCH_KEYSTONE_TLHUTLH.stack())).setParents("void_ingot").setHidden().registerResearch();
	}
}