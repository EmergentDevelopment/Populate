package dev.emergent.populate;

import dev.emergent.populate.blueprint.Blueprint;
import dev.emergent.populate.blueprint.Schematic;
import net.minecraft.world.gen.GenerationStep;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PopulateRegistry {
    private static final Map<GenerationStep.Feature, List<Blueprint>> BLUEPRINTS = new HashMap<>();
    
    public static void init() {
        createDirectory(Paths.get("./populate"));
        
        for (GenerationStep.Feature feature : GenerationStep.Feature.values()) {
            String pathName = feature.toString().toLowerCase();
            createDirectory(Paths.get("./populate/" + pathName));
            BLUEPRINTS.put(feature, getBlueprints(Paths.get("./populate/" + pathName)));
        }
    }
    
    public static List<Blueprint> getBlueprint(GenerationStep.Feature feature) {
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
        
        try (Stream<Path> filePaths = Files.walk(path)) {
            filePaths.filter(Files::isRegularFile).forEach((filePath) -> {
                try {
                    blueprints.add(new Schematic().loadFromFile(filePath));
                } catch (Exception ex) {
                    Populate.LOGGER.warn("Could not load file " + filePath.getFileName().toString(), ex);
                }
            });
        } catch (Exception e) {
            Populate.LOGGER.error("Something went wrong while parsing blueprints", e);
        }
        
        return blueprints;
    }
}
