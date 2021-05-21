package dev.emergent.populate.mixin;

import dev.emergent.populate.settings.FeaturesOptionsScreen;
import dev.emergent.populate.settings.TypesOptionsScreen;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo info){
        this.addButton(new SpruceButtonWidget(Position.of(this.width / 2 - 155, this.height / 6 - 12 + 150), 150, 20, new LiteralText("World Features..."),
                btn -> this.client.openScreen(new TypesOptionsScreen(this))).asVanilla());
    }
}
