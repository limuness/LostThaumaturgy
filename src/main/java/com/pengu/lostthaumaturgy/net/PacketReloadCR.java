package com.pengu.lostthaumaturgy.net;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.lostthaumaturgy.LostThaumaturgy;
import com.pengu.lostthaumaturgy.api.RecipesCrucible;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketReloadCR implements iPacket, iPacketListener<PacketReloadCR, iPacket>
{
	@Override
	public iPacket onArrived(PacketReloadCR packet, MessageContext context)
	{
		LostThaumaturgy.LOG.info("Reloading crucible recipes, from server...");
		RecipesCrucible.reloadRecipes();
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	}
}