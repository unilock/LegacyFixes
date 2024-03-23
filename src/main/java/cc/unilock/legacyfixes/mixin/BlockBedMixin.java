package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import cc.unilock.legacyfixes.mixin.accessor.EntityPlayerAccessor;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBed.class)
public class BlockBedMixin {
    @Inject(method = "Lnet/minecraft/block/BlockBed;onBlockActivated(Lnet/minecraft/world/World;IIILnet/minecraft/entity/player/EntityPlayer;IFFF)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;sleepInBedAt(III)Lnet/minecraft/entity/player/EntityPlayer$EnumStatus;"))
    private void legacyfixes$onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ, CallbackInfoReturnable<Boolean> cir) {
        if (LegacyFixesConfig.bedSpawnFix) {
            ChunkCoordinates spawnChunk = new ChunkCoordinates(x, y, z);
            if (!spawnChunk.equals(((EntityPlayerAccessor) player).getSpawnChunk())) {
                player.setSpawnChunk(spawnChunk, false);
                player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.setSpawnChunk"));
            }
        }
    }
}
