/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit Test for the FramedataImpl1 class
 */
public class FramedataImpl1Test {

    @Test
    public void testGet() {
        FramedataImpl1 binary = FramedataImpl1.get(Opcode.BINARY);
        assertEquals("Frame must be binary", true, binary instanceof BinaryFrame);
        FramedataImpl1 text = FramedataImpl1.get(Opcode.TEXT);
        assertEquals("Frame must be text", true, text instanceof TextFrame);
        FramedataImpl1 closing = FramedataImpl1.get(Opcode.CLOSING);
        assertEquals("Frame must be closing", true, closing instanceof CloseFrame);
        FramedataImpl1 continuous = FramedataImpl1.get(Opcode.CONTINUOUS);
        assertEquals("Frame must be continuous", true, continuous instanceof ContinuousFrame);
        FramedataImpl1 ping = FramedataImpl1.get(Opcode.PING);
        assertEquals("Frame must be ping", true, ping instanceof PingFrame);
        FramedataImpl1 pong = FramedataImpl1.get(Opcode.PONG);
        assertEquals("Frame must be pong", true, pong instanceof PongFrame);
        try {
            FramedataImpl1.get(null);
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException e) {
            //Fine
        }
    }
}
