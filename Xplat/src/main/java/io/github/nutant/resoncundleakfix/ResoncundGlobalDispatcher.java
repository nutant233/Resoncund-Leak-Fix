package io.github.nutant.resoncundleakfix;

import io.github.lounode.eventwrapper.EventsWrapper;
import io.github.lounode.eventwrapper.event.PlayLevelSoundEventWrapper;
import io.github.lounode.eventwrapper.eventbus.api.SubscribeEventWrapper;
import io.github.nutant.resoncundleakfix.mixin.ResoncundSoundInvoker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class ResoncundGlobalDispatcher {

    public static final ResoncundGlobalDispatcher INSTANCE = new ResoncundGlobalDispatcher();

    private final Set<ResoncundSoundInvoker> flowers = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));

    private ResoncundGlobalDispatcher() {
    }

    public static void install() {
        EventsWrapper.register(INSTANCE);
        ResoncundLeakFix.LOG.info("[ResoncundLeakFix] registered one global sound listener for all Resoncund flowers");
    }

    public void track(ResoncundSoundInvoker invoker) {
        flowers.add(invoker);
    }

    public void untrack(ResoncundSoundInvoker invoker) {
        flowers.remove(invoker);
    }

    @SubscribeEventWrapper
    public void onPlayLevelSoundAtPosition(PlayLevelSoundEventWrapper.AtPosition event) {
        var iterator = flowers.iterator();
        while (iterator.hasNext()) {
            var flower = iterator.next();
            if (!isLive(flower, iterator)) {
                continue;
            }
            flower.resoncundleakfix$playAtPosition(event);
        }
    }

    @SubscribeEventWrapper
    public void onPlayLevelSoundAtEntity(PlayLevelSoundEventWrapper.AtEntity event) {
        var iterator = flowers.iterator();
        while (iterator.hasNext()) {
            var flower = iterator.next();
            if (!isLive(flower, iterator)) {
                continue;
            }
            flower.resoncundleakfix$playAtEntity(event);
        }
    }

    private static boolean isLive(ResoncundSoundInvoker flower, Iterator<ResoncundSoundInvoker> iterator) {
        if (flower == null) {
            iterator.remove();
            return false;
        }
        BlockEntity blockEntity = (BlockEntity) flower;
        if (blockEntity.isRemoved()) {
            iterator.remove();
            return false;
        }
        Level level = blockEntity.getLevel();
        if (level == null || level.isClientSide()) {
            iterator.remove();
            return false;
        }
        return true;
    }
}
