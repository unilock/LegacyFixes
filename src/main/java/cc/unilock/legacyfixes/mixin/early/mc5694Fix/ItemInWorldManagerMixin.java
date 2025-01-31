package cc.unilock.legacyfixes.mixin.early.mc5694Fix;

import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.server.management.ItemInWorldManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInWorldManager.class)
public class ItemInWorldManagerMixin {
    @Inject(method = "onBlockClicked(IIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;destroyBlockInWorldPartially(IIIII)V"))
    private void onBlockClicked(int x, int y, int z, int side, CallbackInfo ci) {
        ((ItemInWorldManager) (Object) this).thisPlayerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, ((ItemInWorldManager) (Object) this).theWorld));
    }
}
