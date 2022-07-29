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
import java.io.InputStream;

import org.apache.http.ConnectionClosedException;
import org.apache.http.impl.SessionInputBufferMock;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.EncodingUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestContentLengthInputStream {

    private static final String CONTENT_CHARSET = "ISO-8859-1";

    @Test
    public void testTruncatedContent() throws IOException {
        String correct = "1234567890123456";
        SessionInputBuffer inbuffer = new SessionInputBufferMock(EncodingUtils.getBytes(
                correct, CONTENT_CHARSET));
        InputStream in = new ContentLengthInputStream(inbuffer, 32L);
        byte[] tmp = new byte[32];
        int byteRead = in.read(tmp);
        Assert.assertEquals(16, byteRead);
        try {
            in.read(tmp);
            Assert.fail("ConnectionClosedException should have been closed");
        } catch (ConnectionClosedException ex) {
        }
        try {
            in.read();
            Assert.fail("ConnectionClosedException should have been closed");
        } catch (ConnectionClosedException ex) {
        }
    }

}

