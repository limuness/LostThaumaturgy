package com.pengu.lostthaumaturgy.net;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.lostthaumaturgy.LostThaumaturgy;
import com.pengu.lostthaumaturgy.core.emote.EmoteData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEmote implements iPacket, iPacketListener<PacketEmote, iPacket>
{
	protected EmoteData data;
	
	public PacketEmote(EmoteData data)
	{
		this.data = data;
	}
	
	public PacketEmote()
	{
	}
	
	@Override
	public iPacket onArrived(PacketEmote packet, MessageContext context)
	{
		LostThaumaturgy.proxy.spawnEmote(packet.data, context);
		return null;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		data.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		data = EmoteData.readFromNBT(nbt);
	}
}