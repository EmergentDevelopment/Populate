package dev.emergent.populate.blueprint;

import net.minecraft.nbt.CompoundTag;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Blueprint {
    protected CompoundTag nbtStructure;
    protected String name;
    protected float weight;
    
    public abstract Blueprint load(Path path) throws IOException;
    
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
