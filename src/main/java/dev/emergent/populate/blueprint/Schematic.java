package dev.emergent.populate.blueprint;

import net.minecraft.nbt.NbtIo;

import java.io.IOException;
import java.nio.file.Path;

public class Schematic extends Blueprint {
    @Override
    public Schematic load(Path path) throws IOException {
        this.nbtStructure = NbtIo.readCompressed(path.toFile());
        if (!this.nbtStructure.contains("Blocks")) {
            throw new IllegalArgumentException("NBT structure does not have a \"Blocks\" tag (likely an invalid schematic file)");
        }
        
        this.name = path.getFileName().toString().replace(".schematic", "");
        return this;
    }
    
    public byte[] getData() {
        return this.nbtStructure.getByteArray("Data");
    }
    
    public byte[] getBlocks() {
        return this.nbtStructure.getByteArray("Blocks");
    }
    
    public short getLength() {
        return this.nbtStructure.getShort("Length");
    }
    
    public short getWidth() {
        return this.nbtStructure.getShort("Width");
    }
    
    public short getHeight() {
        return this.nbtStructure.getShort("Height");
    }
}
