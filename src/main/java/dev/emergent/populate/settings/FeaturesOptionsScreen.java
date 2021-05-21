package dev.emergent.populate.settings;

import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.SpruceTexts;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import me.lambdaurora.spruceui.widget.SpruceLabelWidget;
import me.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import me.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import me.lambdaurora.spruceui.widget.container.tabbed.SpruceTabbedWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public class FeaturesOptionsScreen extends SpruceScreen {

    private final Screen parent;

    private SpruceTabbedWidget tabbedWidget;

    public FeaturesOptionsScreen(@Nullable Screen parent, String name) {
        super(new LiteralText(name));
        this.parent = parent;
    }


    @Override
    protected void init() {
        super.init();
        this.tabbedWidget = new SpruceTabbedWidget(Position.of(this, 0, 4), this.width, this.height - 35 - 4, this.title);
        //RAW GENERATION TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Raw Generation"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Raw Generation").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
                    }
            );
            return container;
        });
        //LAKE TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Lakes"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Lakes").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //LOCAL MODIFICATION TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Local Modifications"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Local Modifications").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //UNDERGROUND STRUCTURES TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Underground Structures"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Underground Structures").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });

        //SURFACE STRUCTURES TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Surface Structures"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Surface Structures").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //STRONGHOLDS TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Strongholds"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Strongholds").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //UNDERGROUND ORES TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Underground Ores"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Underground Ores").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //UNDERGROUND DECORATION TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Underground Decoration"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Underground Decoration").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //VEGETAL DECORATION TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Vegetal Decoration"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Vegetal Decoration").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });
        //TOP LAYER MODIFICATION TAB
        this.tabbedWidget.addTabEntry(new LiteralText("Top Layer Modification"), null, (width, height) -> {
            SpruceContainerWidget container = new SpruceContainerWidget(Position.origin(), width, height);
            container.addChildren((containerWidth, containerHeight, widgetAdder) -> {
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 16),
                        new LiteralText("Top Layer Modification").formatted(Formatting.WHITE),
                        containerWidth, true));
                widgetAdder.accept(new SpruceLabelWidget(Position.of(0, 48),
                        new LiteralText("")
                                .formatted(Formatting.WHITE),
                        containerWidth, true));
            });
            return container;
        });

        this.addChild(this.tabbedWidget);

        // Add done button.
        this.addButton(new SpruceButtonWidget(Position.of(this, this.width / 2 - 75, this.height - 29), 150, 20, SpruceTexts.GUI_DONE,
                btn -> this.client.openScreen(this.parent)).asVanilla());
    }

    private SpruceOptionListWidget buildOptions(Position position, int width, int height){
        SpruceOptionListWidget list = new SpruceOptionListWidget(position, width, height);
        return list;
    }

}
