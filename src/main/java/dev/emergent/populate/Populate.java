package dev.emergent.populate;

import me.lortseam.completeconfig.data.Config;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Populate implements ModInitializer {

	public static final String MODID = "populate";
	public static final Logger LOG = LogManager.getLogger(Populate.MODID);

	@Override
	public void onInitialize() {
		// TODO
		
		LOG.info("Populate Initializing");

		Config config = Config.builder(MODID)
				.add(new Settings())
				.build();

		LOG.info("Populate Initialization Complete");
	}
}
