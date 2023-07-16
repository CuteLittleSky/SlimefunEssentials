package me.justahuman.slimefun_essentials.mixins.mbp;

import me.justahuman.slimefun_essentials.client.ResourceLoader;
import mod.omoflop.mbp.client.MBPResourceProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(MBPResourceProvider.class)
public class MBPProviderMixin {
    @Inject(at = @At("HEAD"), method = "provideExtraModels")
    public void addSlimefunModels(ResourceManager manager, Consumer<Identifier> out, CallbackInfo ci) {
        ResourceLoader.loadItems(manager);
        ResourceLoader.loadBlockModels(manager);
        ResourceLoader.getBlockModels().values().forEach(out);
    }
}
