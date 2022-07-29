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

package org.apache.http.entity;

import java.io.ByteArrayOutputStream;

import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link HttpEntityWrapper}.
 *
 */
public class TestHttpEntityWrapper {

    @Test
    public void testBasics() throws Exception {
        String s = "Message content";
        StringEntity httpentity = new StringEntity(s, HTTP.ISO_8859_1);
        httpentity.setContentType(HTTP.PLAIN_TEXT_TYPE);
        httpentity.setContentEncoding(HTTP.IDENTITY_CODING);
        HttpEntityWrapper wrapped = new HttpEntityWrapper(httpentity);

        Assert.assertEquals(httpentity.getContentLength(), wrapped.getContentLength());
        Assert.assertEquals(httpentity.getContentType(), wrapped.getContentType());
        Assert.assertEquals(httpentity.getContentEncoding(), wrapped.getContentEncoding());
        Assert.assertEquals(httpentity.isChunked(), wrapped.isChunked());
        Assert.assertEquals(httpentity.isRepeatable(), wrapped.isRepeatable());
        Assert.assertEquals(httpentity.isStreaming(), wrapped.isStreaming());
        Assert.assertNotNull(wrapped.getContent());
    }

}
