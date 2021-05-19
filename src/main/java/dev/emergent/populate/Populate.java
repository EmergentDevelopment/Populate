package dev.emergent.populate;

import dev.emergent.populate.blueprint.Schematic;
import me.lortseam.completeconfig.data.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Populate implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("populate");
    
    private List<Schematic> trees, ores, structures;
    
    @Override
    public void onInitialize() {
        Populate.LOGGER.info("Initializing Populate");
        
        Config config = Config.builder("populate")
                .add(new Settings())
                .build();
        
        Path treePath = Paths.get("./populate/tree");
        Path orePath = Paths.get("./populate/ore");
        Path structurePath = Paths.get("./populate/structure");
        
        createDirectory(Paths.get("./populate"));
        createDirectory(treePath);
        createDirectory(orePath);
        createDirectory(structurePath);
        
        trees = getSchematics(treePath);
        ores = getSchematics(orePath);
        structures = getSchematics(structurePath);
        
        Populate.LOGGER.info("Populate Initialization Complete");
    }
    
    private void createDirectory(Path path) {
        try {
            Files.createDirectory(path);
        } catch (Exception ex) {
            Populate.LOGGER.warn("Could not create directory " + path.toAbsolutePath().toString(), ex);
        }
    }
    
    private List<Schematic> getSchematics(Path path) {
        List<Schematic> schematics = new ArrayList<>();
        
        try (Stream<Path> filePaths = Files.walk(path)) {
            filePaths.filter(Files::isRegularFile).forEach((filePath) -> {
                try {
                    schematics.add(new Schematic().load(filePath));
                } catch (IOException ex) {
                    Populate.LOGGER.warn("Could not load file " + filePath.getFileName().toString(), ex);
                }
            });
        } catch (Exception e) {
            Populate.LOGGER.error("Something went wrong while parsing schematics", e);
        }
        
        return schematics;
    }
}
