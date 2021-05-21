package dev.emergent.populate.util.registries;

import dev.emergent.populate.Populate;
import dev.emergent.populate.blueprint.Blueprint;
import dev.emergent.populate.blueprint.Schematic;
import net.minecraft.world.gen.GenerationStep;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FilePathRegistry {

    public static final Map<GenerationStep.Feature, List<Blueprint>> BLUEPRINTS = new HashMap<>();

    private static void createDirectory(Path path) {
        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException ignored) {
        } catch (Exception ex) {
            Populate.LOGGER.warn("Could not create directory " + path.toAbsolutePath().toString(), ex);
        }
        Populate.LOGGER.info(path + " Generated");
    }

    private static List<Blueprint> getBlueprints(Path path) {
        List<Blueprint> schematics = new ArrayList<>();

        try (Stream<Path> filePaths = Files.walk(path)) {
            filePaths.filter(Files::isRegularFile).forEach((filePath) -> {
                try {
                    schematics.add(new Schematic().loadFromFile(filePath));
                } catch (Exception ex) {
                    Populate.LOGGER.warn("Could not load file " + filePath.getFileName().toString(), ex);
                }
            });
        } catch (Exception e) {
            Populate.LOGGER.error("Something went wrong while parsing schematics", e);
        }

        return schematics;
    }

    private static void setFilePaths() {
        createDirectory(Paths.get("./populate"));

        for (GenerationStep.Feature currentFeature :
                GenerationStep.Feature.values()) {
            String pathName = currentFeature.toString().toLowerCase();
            createDirectory(Paths.get("./populate/" + pathName));
            BLUEPRINTS.put(currentFeature, getBlueprints(Paths.get("./populate/" + pathName)));
        }
    }

    public static void init() {
        setFilePaths();
    }

}
