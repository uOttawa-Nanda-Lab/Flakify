/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.impl.SessionOutputBufferMock;
import org.junit.Assert;
import org.junit.Test;

public class TestIdentityOutputStream {

    @Test
    public void testClosedCondition() throws Exception {
        SessionOutputBufferMock transmitter = new SessionOutputBufferMock();
        IdentityOutputStream outstream = new IdentityOutputStream(transmitter);
        outstream.close();
        outstream.close();

        try {
            byte[] tmp = new byte[2];
            outstream.write(tmp, 0, tmp.length);
            Assert.fail("IOException should have been thrown");
        } catch (IOException e) {
            //expected
        }
        try {
            outstream.write('a');
            Assert.fail("IOException should have been thrown");
        } catch (IOException e) {
            //expected
        }
    }


}

