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
    public void testAppend() {
        FramedataImpl1 frame0 = FramedataImpl1.get(Opcode.BINARY);
        frame0.setFin(false);
        frame0.setPayload(ByteBuffer.wrap("first".getBytes()));
        FramedataImpl1 frame1 = FramedataImpl1.get(Opcode.BINARY);
        frame1.setPayload(ByteBuffer.wrap("second".getBytes()));
        frame0.append(frame1);
        assertEquals("Fin must be set", true, frame0.isFin());
        assertArrayEquals("Payload must be equal", "firstsecond".getBytes(), frame0.getPayloadData().array());
    }
}
