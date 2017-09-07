package com.pengu.lostthaumaturgy.intr.hc;

import com.pengu.hammercore.api.mhb.RaytracePlugin;
import com.pengu.hammercore.api.mhb.iRayCubeRegistry;
import com.pengu.hammercore.api.mhb.iRayRegistry;
import com.pengu.lostthaumaturgy.core.block.BlockAdvancedVisValve;
import com.pengu.lostthaumaturgy.core.block.BlockConduit;
import com.pengu.lostthaumaturgy.core.block.BlockPressurizedConduit;
import com.pengu.lostthaumaturgy.core.block.BlockVisValve;
import com.pengu.lostthaumaturgy.core.block.wood.silverwood.BlockSilverwoodConduit;
import com.pengu.lostthaumaturgy.init.BlocksLT;

@RaytracePlugin
public class LTRaytracePlugin implements iRayRegistry
{
	@Override
	public void registerCubes(iRayCubeRegistry cube)
	{
		cube.bindBlockCubeManager((BlockConduit) BlocksLT.CONDUIT, (BlockConduit) BlocksLT.CONDUIT);
		cube.bindBlockCubeManager((BlockSilverwoodConduit) BlocksLT.CONDUIT_SILVERWOOD, (BlockSilverwoodConduit) BlocksLT.CONDUIT_SILVERWOOD);
		cube.bindBlockCubeManager((BlockVisValve) BlocksLT.VIS_VALVE, (BlockVisValve) BlocksLT.VIS_VALVE);
		cube.bindBlockCubeManager((BlockAdvancedVisValve) BlocksLT.ADVANCED_VIS_VALVE, (BlockAdvancedVisValve) BlocksLT.ADVANCED_VIS_VALVE);
		cube.bindBlockCubeManager((BlockPressurizedConduit) BlocksLT.PRESSURIZED_CONDUIT, (BlockPressurizedConduit) BlocksLT.PRESSURIZED_CONDUIT);
	}
}