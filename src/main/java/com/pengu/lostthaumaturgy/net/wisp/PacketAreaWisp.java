package com.pengu.lostthaumaturgy.net.wisp;

import java.util.Random;

import com.pengu.hammercore.net.packetAPI.iPacket;
import com.pengu.hammercore.net.packetAPI.iPacketListener;
import com.pengu.hammercore.proxy.ParticleProxy_Client;
import com.pengu.lostthaumaturgy.client.fx.FXWisp;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketAreaWisp implements iPacket, iPacketListener<PacketAreaWisp, iPacket>
{
	AxisAlignedBB aabb;
	float partialTicks;
	int max, type;
	
	public PacketAreaWisp(int max, AxisAlignedBB aabb, float partialTicks, int type)
	{
		this.aabb = aabb;
		this.partialTicks = partialTicks;
		this.max = max;
		this.type = type;
	}
	
	public PacketAreaWisp()
	{
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setFloat("p1", partialTicks);
		nbt.setInteger("p2", max);
		nbt.setInteger("p3", type);
		nbt.setDouble("p4", aabb.minX);
		nbt.setDouble("p5", aabb.minY);
		nbt.setDouble("p6", aabb.minZ);
		nbt.setDouble("p7", aabb.maxX);
		nbt.setDouble("p8", aabb.maxY);
		nbt.setDouble("p9", aabb.maxZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		partialTicks = nbt.getFloat("p1");
		max = nbt.getInteger("p2");
		type = nbt.getInteger("p3");
		aabb = new AxisAlignedBB(nbt.getDouble("p4"), nbt.getDouble("p5"), nbt.getDouble("p6"), nbt.getDouble("p7"), nbt.getDouble("p8"), nbt.getDouble("p9"));
	}
	
	@Override
	public iPacket onArrived(PacketAreaWisp packet, MessageContext context)
	{
		if(context.side == Side.CLIENT)
			packet.client();
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void client()
	{
		Random rand = Minecraft.getMinecraft().world.rand;
		for(int i = 0; i < max; ++i)
		{
			double x = aabb.minX + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxX - aabb.minX);
			double y = aabb.minY + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxY - aabb.minY);
			double z = aabb.minZ + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxZ - aabb.minZ);
			
			double x2 = aabb.minX + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxX - aabb.minX);
			double y2 = aabb.minY + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxY - aabb.minY);
			double z2 = aabb.minZ + (rand.nextDouble() - rand.nextDouble()) * (aabb.maxZ - aabb.minZ);
			
			ParticleProxy_Client.queueParticleSpawn(new FXWisp(Minecraft.getMinecraft().world, x, y, z, x2, y2, z2, partialTicks, type));
		}
	}
}