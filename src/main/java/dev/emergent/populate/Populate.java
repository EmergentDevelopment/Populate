package dev.emergent.populate;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Populate implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("populate");

    @Override
    public void onInitialize() {
        Populate.LOGGER.info("Initializing Populate");

        PopulateRegistry.init();

        Populate.LOGGER.info("Populate Initialization Complete");
    }

}

