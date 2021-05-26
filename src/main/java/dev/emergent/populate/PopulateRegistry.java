package dev.emergent.populate;

import dev.emergent.populate.blueprint.Blueprint;
import dev.emergent.populate.blueprint.Schematic;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopulateRegistry {
    private static final Map<GenerationStep.Feature, List<Blueprint>> BLUEPRINTS = new HashMap<>();
    
    public static void init() {
        for (Identifier identifier : Registry.BLOCK.getIds()) {
            Block block = Registry.BLOCK.get(identifier);
            int id = Registry.BLOCK.getRawId(block);
            
            Blueprint.FALLBACK_BLOCK_PALETTE.put(id, block);
        }
        
        createDirectory(Paths.get("populate"));
        
        for (GenerationStep.Feature feature : GenerationStep.Feature.values()) {
            String pathName = feature.toString().toLowerCase();
            createDirectory(Paths.get("populate/" + pathName));
            BLUEPRINTS.put(feature, getBlueprints(Paths.get("populate/" + pathName)));
        }
    }
    
    public static List<Blueprint> getBlueprints(GenerationStep.Feature feature) {
        return BLUEPRINTS.get(feature);
    }
    
    private static void createDirectory(Path path) {
        if (Files.exists(path)) return;
        
        try {
            Files.createDirectory(path);
            Populate.LOGGER.info("Created directory " + path.toString());
        } catch (Exception ex) {
            Populate.LOGGER.warn("Could not create directory " + path.toString(), ex);
        }
    }
    
    private static List<Blueprint> getBlueprints(Path path) {
        List<Blueprint> blueprints = new ArrayList<>();
        
        try {
            List<Path> filePaths = Files.walk(path)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            
            for (Path filePath : filePaths) {
                try {
                    Blueprint blueprint = new Schematic(filePath); // TODO: Structure NBT files.
                    
                    blueprint.loadFromDisk();
                    blueprints.add(blueprint);
                } catch (Exception ex) {
                    Populate.LOGGER.warn("Could not load file " + filePath.getFileName().toString(), ex);
                }
            }
        } catch (Exception e) {
            Populate.LOGGER.error("Something went wrong while parsing blueprints", e);
        }
        
        return blueprints;
    }
}
