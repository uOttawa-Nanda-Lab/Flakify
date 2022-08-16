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
import org.java_websocket.exceptions.InvalidDataException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit Test for the CloseFrame class
 */
public class CloseFrameTest {

    @Test
    public void testIsValid() {
        CloseFrame frame = new CloseFrame();
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setFin(false);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setFin(true);
        frame.setRSV1(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV1(false);
        frame.setRSV2(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV2(false);
        frame.setRSV3(true);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //Fine
        }
        frame.setRSV3(false);
        frame.setCode(CloseFrame.NORMAL);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.GOING_AWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.PROTOCOL_ERROR);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.REFUSE);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.NOCODE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.ABNORMAL_CLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.POLICY_VALIDATION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TOOBIG);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.EXTENSION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.UNEXPECTED_CONDITION);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.SERVICE_RESTART);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TRY_AGAIN_LATER);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.BAD_GATEWAY);
        try {
            frame.isValid();
        } catch (InvalidDataException e) {
            fail("InvalidDataException should not be thrown");
        }
        frame.setCode(CloseFrame.TLS_ERROR);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NEVER_CONNECTED);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.BUGGYCLOSE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.FLASHPOLICY);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NOCODE);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NO_UTF8);
        frame.setReason(null);
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
        frame.setCode(CloseFrame.NOCODE);
        frame.setReason("Close");
        try {
            frame.isValid();
            fail("InvalidDataException should be thrown");
        } catch (InvalidDataException e) {
            //fine
        }
    }
}
