package com.pengu.lostthaumaturgy.api.tiles;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityVisConnection
{
	@CapabilityInject(iConnection.class)
	public static Capability<iConnection> VIS = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(iConnection.class, new IStorage<iConnection>()
		{
			@Override
			public NBTBase writeNBT(Capability<iConnection> capability, iConnection instance, EnumFacing side)
			{
				NBTTagCompound c = new NBTTagCompound();
				c.setFloat("PureVis", instance.getPureVis());
				c.setFloat("TaintedVis", instance.getTaintedVis());
				c.setFloat("MaxVis", instance.getMaxVis());
				return c;
			}
			
			@Override
			public void readNBT(Capability<iConnection> capability, iConnection instance, EnumFacing side, NBTBase nbt)
			{
				if(nbt instanceof NBTTagCompound)
				{
					instance.setPureVis(((NBTTagCompound) nbt).getFloat("PureVis"));
					instance.setTaintedVis(((NBTTagCompound) nbt).getFloat("TaintedVis"));
				}
			}
		}, new Callable<iConnection>()
		{
			@Override
			public iConnection call() throws Exception
			{
				return null;
			}
		});
	}
}