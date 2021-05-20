package dev.emergent.populate.blueprint;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a file format which contains NBT data describing an arrangement of blocks that comprise a structure.
 */
public abstract class Blueprint {
    protected Map<BlockPos, Block> blocks = new HashMap<>();
    protected String name;
    protected float weight;
    
    /**
     * Reads the file at the specified path and parses relevant block data.
     *
     * @param filePath Desired NBT file to parse.
     */
    public abstract Blueprint loadFromFile(Path filePath) throws IOException;
    
    /**
     * Places the blocks at a point in the world.
     * @param pos
     * @param world
     */
    public abstract boolean placeInWorld(BlockPos pos, World world);
    
    public String getName() {
        return name;
    }
    
    public float getWeight() {
        return weight;
    }
    
    protected void setWeight(float weight) {
        this.weight = weight;
    }
}
