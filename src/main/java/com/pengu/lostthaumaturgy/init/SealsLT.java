package com.pengu.lostthaumaturgy.init;

import java.util.function.Function;

import com.pengu.lostthaumaturgy.LostThaumaturgy;
import com.pengu.lostthaumaturgy.api.seal.SealCombination;
import com.pengu.lostthaumaturgy.api.seal.SealInstance;
import com.pengu.lostthaumaturgy.api.seal.SealManager;
import com.pengu.lostthaumaturgy.core.tile.TileSeal;
import com.pengu.lostthaumaturgy.custom.seals.earth.SealTillSoil;
import com.pengu.lostthaumaturgy.custom.seals.water.SealWaterHydrate;

public class SealsLT
{
	private static int seals;
	
	public static void init()
	{
		LostThaumaturgy.LOG.info("Registering seals...");
		
		register(new SealCombination(ItemsLT.RUNICESSENCE_WATER, null, null), seal -> new SealWaterHydrate(seal), "Water, None, None");
		register(new SealCombination(ItemsLT.RUNICESSENCE_EARTH, ItemsLT.RUNICESSENCE_EARTH, ItemsLT.RUNICESSENCE_EARTH), seal -> new SealTillSoil(seal), "Earth, Earth, Earth");
		
		LostThaumaturgy.LOG.info("-Registered " + seals + " seals.");
	}
	
	private static void register(SealCombination combo, Function<TileSeal, SealInstance> obtainer, String name)
	{
		LostThaumaturgy.LOG.info(" -" + name + "...");
		SealManager.registerCombination(combo, obtainer);
		++seals;
	}
}