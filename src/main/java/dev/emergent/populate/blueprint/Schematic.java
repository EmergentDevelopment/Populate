package dev.emergent.populate.blueprint;

import dev.emergent.populate.Populate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Pre-flattening schematic format used by Schematica and MCEdit.
 */
public class Schematic extends Blueprint {
    public Schematic(Path filePath) {
        super(filePath);
    }
    
    @Override
    public void loadFromDisk() throws IOException, IllegalArgumentException {
        CompoundTag nbtStructure = NbtIo.readCompressed(filePath.toFile());
        
        if (!nbtStructure.contains("Length") || !nbtStructure.contains("Width") ||
            !nbtStructure.contains("Height") || !nbtStructure.contains("Blocks")) {
            
            throw new IllegalArgumentException("Invalid schematic file; NBT structure is missing tags for block data.");
        }
        
        this.name = filePath.getFileName().toString();
        Path shortPath = filePath.subpath(filePath.getNameCount() - 2, filePath.getNameCount());
        
        if (nbtStructure.contains("SchematicaMapping")) {
            CompoundTag paletteTag = nbtStructure.getCompound("SchematicaMapping");
            
            for (String key : paletteTag.getKeys()) {
                int paletteKey = paletteTag.getShort(key);
                Optional<Block> paletteValue = Registry.BLOCK.getOrEmpty(new Identifier(key));
                
                if (!paletteValue.isPresent()) {
                    Populate.LOGGER.warn(shortPath.toString() + " contains an unknown block: " + key);
                    this.blockPaletteMap.put(paletteKey, Blocks.BARRIER); // TODO: data-driven MC version compatibility
                    continue;
                }
                
                this.blockPaletteMap.put(paletteKey, paletteValue.get());
            }
            
        } else { // TODO: MCEdit 2 palette support
            Populate.LOGGER.info(
                    shortPath.toString() + " did not contain a supported palette format. " +
                    "The fallback block palette will be used instead; some things may not look right!"
            );
            
            this.blockPaletteMap = Blueprint.FALLBACK_BLOCK_PALETTE;
        }
        
        byte[] blockIds = nbtStructure.getByteArray("Blocks");
        byte[] additionalNibbles = null;
        
        if (nbtStructure.contains("AddBlocks")) { // TODO: old schematica format support
            byte[] additionalBytes = nbtStructure.getByteArray("AddBlocks");
            additionalNibbles = new byte[additionalBytes.length * 2];
    
            for (int index = 0; index < additionalBytes.length; index++) {
                additionalNibbles[index * 2] = (byte) ((additionalBytes[index] >> 4) & 0xF);
                additionalNibbles[index * 2 + 1] = (byte) ((additionalBytes[index]) & 0xF);
            }
        }
        
        int length = nbtStructure.getInt("Length");
        int width = nbtStructure.getInt("Width");
        int height = nbtStructure.getInt("Height");
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    int blockID = blockIds[index] & 0xFF;
                    
                    if (additionalNibbles != null) {
                        blockID |= additionalNibbles[index] << 8;
                    }
                    
                    this.blockOffsetMap.put(
                            new BlockPos(x, y, z),
                            Registry.BLOCK.get(blockID)
                    );
                }
            }
        }
    }
    
    @Override
    public void placeInWorld(BlockPos pos, World world) {
        // TODO: implement this
    }
}
