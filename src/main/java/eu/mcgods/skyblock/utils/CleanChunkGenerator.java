package eu.mcgods.skyblock.utils;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class CleanChunkGenerator extends ChunkGenerator {
	
	@SuppressWarnings("deprecation")
	@Override
	public ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z, @Nonnull BiomeGrid biomes) {
		
		for(int X = 0; X < 16; X++) {
			for(int Z = 0; Z < 16; Z++) {
				if(biomes.getBiome(X, Z) != Biome.PLAINS) {
					biomes.setBiome(X, Z, Biome.PLAINS);
				}
			}
		}
		
		return createChunkData(world);
	}
}