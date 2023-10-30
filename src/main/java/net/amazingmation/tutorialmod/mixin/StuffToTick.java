package net.amazingmation.tutorialmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class StuffToTick {
    public static StuffToTick.StuffTimerAccess StuffTimerAccess;

    @Mixin(StuffToTick.class) // ServerWorld, MinecraftServer, etc
    public static class StuffTimer implements StuffTimerAccess {
        @Unique
        private long ticksUntilSomething;

        @Inject(method = "tick", at = @At("TAIL"))
        private void onTick(CallbackInfo ci) { // Fix parameters as needed
            if (--this.ticksUntilSomething == 0L) {
                // If you want to repeat this, reset ticksUntilSomething here.
            }
        }

        @Override
        public void tutorial_mod_setTimer(long ticksUntilSomething) {
            this.ticksUntilSomething = ticksUntilSomething;
        }
    }

    public interface StuffTimerAccess {
        void tutorial_mod_setTimer(long ticksUntilSomething);
    }
}
