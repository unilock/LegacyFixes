/**
 * Adapted from the Minecraft mod "copycore" by copygirl.
 * <p/>
 * Copyright (c) 2014 copygirl
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cc.unilock.legacyfixes.module;

import cc.unilock.legacyfixes.util.BlockLocation;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockDoor;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DoubleDoorsModule {
    private static boolean handling = false;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.world == null || event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.entityPlayer.isSneaking() || event.isCanceled() || event.getResult() == Event.Result.DENY || event.useBlock == Event.Result.DENY || handling) {
            return;
        }

        handling = true;

        BlockLocation loc = BlockLocation.get(event.world, event.x, event.y, event.z);

        if (loc.getBlock() instanceof BlockDoor door) {
            int direction = getDoorDirection(door, loc);
            boolean isOpen = isDoorOpen(door, loc);
            boolean isMirrored = isDoorMirrored(door, loc);

            int i = (isMirrored ? -1 : 1);
            switch (direction) {
                case 0: loc = loc.relative(0, 0,  i); break;
                case 1: loc = loc.relative(-i, 0, 0); break;
                case 2: loc = loc.relative(0, 0, -i); break;
                case 3: loc = loc.relative( i, 0, 0); break;
            }

            if (loc.getBlock() == door && getDoorDirection(door, loc) == direction && isDoorOpen(door, loc) == isOpen && isDoorMirrored(door, loc) != isMirrored) {
                door.onBlockActivated(loc.world, loc.x, loc.y, loc.z, event.entityPlayer, event.face, 0, 0,0);
            }
        }

        handling = false;
    }

    private static int getDoorDirection(BlockDoor door, BlockLocation loc) {
        return door.func_150013_e(loc.blockAccess, loc.x, loc.y, loc.z);
    }

    private static boolean isDoorOpen(BlockDoor door, BlockLocation loc) {
        return door.func_150015_f(loc.blockAccess, loc.x, loc.y, loc.z);
    }

    private static boolean isDoorMirrored(BlockDoor door, BlockLocation loc) {
        return (door.func_150012_g(loc.blockAccess, loc.x, loc.y, loc.z) & 16) != 0;
    }
}
