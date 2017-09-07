package com.pengu.lostthaumaturgy.net.wisp;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.hammercore.proxy.ParticleProxy_Client;
import com.pengu.lostthaumaturgy.client.fx.FXBubble;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketFXBubble implements iPacket, iPacketListener<PacketFXBubble, iPacket>
{
	double x, y, z;
	
	public PacketFXBubble(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public PacketFXBubble()
	{
	}
	
	@Override
	public iPacket onArrived(PacketFXBubble packet, MessageContext context)
	{
		if(context.side == Side.CLIENT)
			packet.summon();
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	private void summon()
	{
		ParticleProxy_Client.queueParticleSpawn(new FXBubble(Minecraft.getMinecraft().world, new Vec3d(x, y, z)));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("x", x);
		nbt.setDouble("y", y);
		nbt.setDouble("z", z);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		x = nbt.getDouble("x");
		y = nbt.getDouble("y");
		z = nbt.getDouble("z");
	}
}