package com.pengu.lostthaumaturgy.net;

import java.util.ArrayList;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.lostthaumaturgy.api.research.client.ClientResearchData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUpdateClientRD implements iPacket, iPacketListener<PacketUpdateClientRD, iPacket>
{
	public NBTTagCompound nbt;
	
	public PacketUpdateClientRD(ArrayList<String> researches)
	{
		nbt = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		if(researches != null)
			for(String r : researches)
				list.appendTag(new NBTTagString(r));
		nbt.setTag("Research", list);
	}
	
	public PacketUpdateClientRD()
	{
	}
	
	@Override
	public iPacket onArrived(PacketUpdateClientRD packet, MessageContext context)
	{
		if(context.side == Side.CLIENT)
			ClientResearchData.fromNBT(packet.nbt);
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setTag("Data", this.nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.nbt = nbt.getCompoundTag("Data");
	}
}