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
    public static Map<Integer, Block> FALLBACK_BLOCK_PALETTE;
    
    protected final Path filePath;
    
    protected Map<Integer, Block> blockPaletteMap;
    protected Map<BlockPos, Block> blockOffsetMap;
    protected String name;
    protected float weight;
    
    protected Blueprint(Path filePath) {
        blockPaletteMap = new HashMap<>();
        blockOffsetMap = new HashMap<>();
        
        this.filePath = filePath.toAbsolutePath();
    }
    
    static {
        FALLBACK_BLOCK_PALETTE = new HashMap<>();
    }
    
    /**
     * Attempts to read the file at the path associated with this object and parses relevant data.
     */
    public abstract void loadFromDisk() throws IOException;
    
    /**
     * Places this structure at a point in the world.
     */
    public abstract void placeInWorld(BlockPos pos, World world);
    
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
