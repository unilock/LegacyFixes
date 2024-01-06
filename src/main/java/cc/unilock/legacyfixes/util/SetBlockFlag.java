/**
 * Borrowed from the Minecraft mod "copycore" by copygirl.
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
package cc.unilock.legacyfixes.util;

public final class SetBlockFlag {

    /** When set, causes a block update to the surrounding blocks. */
    public static final int BLOCK_UPDATE   = 0x1;
    /** When set on server, sends the block change to the client. */
    public static final int SEND_TO_CLIENT = 0x2;
    /** When set on client, will not render the chunk again. */
    public static final int DONT_RERENDER  = 0x4;

    /** The default setting, will cause a block
     *  update and send the change to the client. */
    public static final int DEFAULT = BLOCK_UPDATE | SEND_TO_CLIENT;

    private SetBlockFlag() {  }

}
