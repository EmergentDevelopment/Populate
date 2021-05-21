package dev.emergent.populate.settings;

import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.SpruceTexts;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.Nullable;

public class TypesOptionsScreen extends SpruceScreen {

    private final Screen parent;

    public TypesOptionsScreen(@Nullable Screen parent) {
        super(new LiteralText("World Features"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        int startY = this.height / 10 + 28;
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 + 5, startY), 140, 20, new LiteralText("Raw Generation"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Raw Generation"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 + 5, startY += 25), 140, 20, new LiteralText("Lakes"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Lakes"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 + 5, startY += 25), 140, 20, new LiteralText("Local Modifications"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Local Modifications"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 + 5, startY += 25), 140, 20, new LiteralText("Underground Structures"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Underground Structures"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 + 5, startY += 25), 140, 20, new LiteralText("Surface Structures"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Surface Structures"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 145, startY -= 100), 140, 20, new LiteralText("Strongholds"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Strongholds"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 145, startY += 25), 140, 20, new LiteralText("Underground Ores"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Underground Ores"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 145, startY += 25), 140, 20, new LiteralText("Underground Decoration"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Underground Decoration"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 145, startY += 25), 140, 20, new LiteralText("Vegetal Decoration"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Vegetal Decoration"))));
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 145, startY += 25), 140, 20, new LiteralText("Top Layer Modification"),
                btn -> this.client.openScreen(new FeaturesOptionsScreen(this, "Top Layer Modification"))));

        // Add done button.
        this.addChild(new SpruceButtonWidget(Position.of(this, this.width / 2 - 75, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.openScreen(this.parent)));
    }

    @Override
    public void renderTitle(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
    }
}
