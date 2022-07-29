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

    @Test public void testSetters(){FramedataImpl1 frame=FramedataImpl1.get(Opcode.BINARY);frame.setFin(false);assertEquals("Fin must not be set",false,frame.isFin());frame.setTransferemasked(true);assertEquals("TransferedMask must be set",true,frame.getTransfereMasked());ByteBuffer buffer=ByteBuffer.allocate(100);frame.setPayload(buffer);assertEquals("Payload must be of size 100",100,frame.getPayloadData().capacity());frame.setRSV1(true);assertEquals("RSV1 must be true",true,frame.isRSV1());frame.setRSV2(true);assertEquals("RSV2 must be true",true,frame.isRSV2());frame.setRSV3(true);assertEquals("RSV3 must be true",true,frame.isRSV3());}
}
