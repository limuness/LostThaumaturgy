package com.pengu.lostthaumaturgy.client;

import com.pengu.lostthaumaturgy.custom.aura.ThaumosphereChunk;

public class ClientAtmosphereChunk
{
	private static ThaumosphereChunk client_chunk;
	
	public static void setClientChunk(ThaumosphereChunk client_chunk)
	{
		ClientAtmosphereChunk.client_chunk = client_chunk;
	}
	
	public static ThaumosphereChunk getClientChunk()
	{
		if(client_chunk == null)
			client_chunk = new ThaumosphereChunk();
		return client_chunk;
	}
}