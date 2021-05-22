package dev.emergent.populate.blueprint;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.io.IOException;
import java.nio.file.Path;

public class Schematic extends Blueprint {
    @Override
    public Schematic loadFromFile(Path filePath) throws IOException, IllegalArgumentException {
        CompoundTag nbtStructure = NbtIo.readCompressed(filePath.toFile());
        
        if (!nbtStructure.contains("Blocks") && !nbtStructure.contains("AddBlocks") && !nbtStructure.contains("Add")) {
            throw new IllegalArgumentException("Invalid schematic file; NBT structure is missing tags for block data.");
        }
        
        this.name = filePath.toString();
        
        byte[] upperBlockIdBits = new byte[0];
        byte[] lowerBlockIdBits = nbtStructure.getByteArray("Blocks");
        byte[] blockstates = nbtStructure.getByteArray("Data");
        
        if (nbtStructure.contains("AddBlocks")) {
            byte[] extraBlocksNibble = nbtStructure.getByteArray("AddBlocks");
            upperBlockIdBits = new byte[extraBlocksNibble.length * 2];
            
            for (int i = 0; i < extraBlocksNibble.length; i++) {
                upperBlockIdBits[i * 2] = (byte) ((extraBlocksNibble[i] >> 8) & 255);
                upperBlockIdBits[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 255);
            }
        } else if (nbtStructure.contains("Add")) {
            upperBlockIdBits = nbtStructure.getByteArray("Add");
        }
        
        short length = nbtStructure.getShort("Length");
        short width = nbtStructure.getShort("Width");
        short height = nbtStructure.getShort("Height");
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < length; z++) {
                    int index = x + (y * length + z) * width;
                    short blockId;
                    
                    if ((index >> 1) >= upperBlockIdBits.length) {
                        blockId = (short) (lowerBlockIdBits[index] & 0xFF);
                    } else {
                        byte upperBit = upperBlockIdBits[index >> 1];
                        int upperId = (((index & 1) == 0) ? ((upperBit & 0x0F) << 8) : ((upperBit & 0xF0) << 4));
                        blockId = (short) (upperId + (lowerBlockIdBits[index] & 0xFF));
                    }
                    
                    BlockPos pos = new BlockPos(x, y, z);
                    Block block = Registry.BLOCK.get(blockId);
                    BlockState state = Block.getStateFromRawId(blockstates[index] & 255);
                    
                    this.blocks.put(pos, block);
                }
            }
        }
        
        return this;
    }
    
    @Override
    public boolean placeInWorld(BlockPos pos, World world) {
        return false;
    }
}
