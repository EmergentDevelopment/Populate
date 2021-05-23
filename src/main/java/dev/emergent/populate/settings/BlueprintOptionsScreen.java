package dev.emergent.populate.settings;

import dev.emergent.populate.PopulateRegistry;
import dev.emergent.populate.blueprint.Blueprint;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.SpruceTexts;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import me.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import me.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.world.gen.GenerationStep;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlueprintOptionsScreen extends SpruceScreen {
    
    private final Screen parent;
    private final GenerationStep.Feature feature;
    
    private SpruceTabbedWidget tabbedWidget;
    
    protected BlueprintOptionsScreen(@Nullable Screen parent, GenerationStep.Feature feature, String name) {
        super(new LiteralText(name));
        this.parent = parent;
        this.feature = feature;
    }
    
    private void blueprintWidget(GenerationStep.Feature feature) {
        List<Blueprint> featureType = PopulateRegistry.getBlueprints(feature);
        for (Blueprint currentBlueprint : featureType) {
            this.tabbedWidget.addTabEntry(new LiteralText(currentBlueprint.getName()), null, (width, height) ->
                    buildOptionList(Position.origin(), width, height));
        }
    }
    
    public SpruceOptionListWidget buildOptionList(Position position, int width, int height) {
        SpruceOptionListWidget list = new SpruceOptionListWidget(position, width, height);
        
        return list;
    }
    
    @Override
    protected void init() {
        super.init();
        this.tabbedWidget = new SpruceTabbedWidget(Position.of(this, 0, 4), this.width, this.height - 35 - 4, this.title);
        //Generating Blueprint Widgets
        blueprintWidget(feature);
        
        this.addChild(this.tabbedWidget);
        
        // Add done button.
        this.addButton(new SpruceButtonWidget(Position.of(this, this.width / 2 - 75, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.openScreen(this.parent)).asVanilla());
    }
}
