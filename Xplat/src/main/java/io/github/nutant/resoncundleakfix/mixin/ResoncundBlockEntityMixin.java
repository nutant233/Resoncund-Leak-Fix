package io.github.nutant.resoncundleakfix.mixin;

import io.github.nutant.resoncundleakfix.ResoncundGlobalDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "io.github.lounode.extrabotany.common.block.flower.generating.ResoncundBlockEntity")
public abstract class ResoncundBlockEntityMixin extends BlockEntity {

    protected ResoncundBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lio/github/lounode/eventwrapper/EventsWrapper;register(Ljava/lang/Object;)V", remap = false), require = 0)
    private void resoncundleakfix$suppressPerInstanceRegistration(Object target) {}

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        if (!(this instanceof ResoncundSoundInvoker invoker)) {
            return;
        }
        Level level = this.getLevel();
        if (level == null || level.isClientSide()) {
            return;
        }
        ResoncundGlobalDispatcher.INSTANCE.track(invoker);
    }

    @Override
    public void setRemoved() {
        if (this instanceof ResoncundSoundInvoker invoker) {
            ResoncundGlobalDispatcher.INSTANCE.untrack(invoker);
        }
        super.setRemoved();
    }
}
