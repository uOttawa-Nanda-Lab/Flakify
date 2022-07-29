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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.protocol.HTTP;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link FileEntity}.
 *
 */
public class TestFileEntity {

    @Test
    public void testBasics() throws Exception {
        File tmpfile = File.createTempFile("testfile", ".txt");
        tmpfile.deleteOnExit();
        FileEntity httpentity = new FileEntity(tmpfile,
                ContentType.create("text/plain", HTTP.ISO_8859_1));

        Assert.assertEquals(tmpfile.length(), httpentity.getContentLength());
        final InputStream content = httpentity.getContent();
        Assert.assertNotNull(content);
        content.close();
        Assert.assertTrue(httpentity.isRepeatable());
        Assert.assertFalse(httpentity.isStreaming());
        if (!tmpfile.delete()){
            Assert.fail("Failed to delete: "+tmpfile);
        }
    }

}
