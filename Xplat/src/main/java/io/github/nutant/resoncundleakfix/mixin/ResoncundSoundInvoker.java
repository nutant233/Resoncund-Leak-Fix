package io.github.nutant.resoncundleakfix.mixin;

import io.github.lounode.eventwrapper.event.PlayLevelSoundEventWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "io.github.lounode.extrabotany.common.block.flower.generating.ResoncundBlockEntity")
public interface ResoncundSoundInvoker {

    @Invoker(value = "onPlayLevelSound" ,remap = false)
    void resoncundleakfix$playAtPosition(PlayLevelSoundEventWrapper.AtPosition event);

    @Invoker(value = "onPlayLevelSound",remap = false)
    void resoncundleakfix$playAtEntity(PlayLevelSoundEventWrapper.AtEntity event);
}
