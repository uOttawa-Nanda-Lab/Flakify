/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class StreamUtilsTest {

    @Test public void testLimitedInputStream() throws Exception{InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");assertThat(10,is(is.available()));is=StreamUtils.limitedInputStream(is,2);assertThat(2,is(is.available()));assertThat(is.markSupported(),is(true));is.mark(0);assertEquals((int)'0',is.read());assertEquals((int)'1',is.read());assertEquals(-1,is.read());is.reset();is.skip(1);assertEquals((int)'1',is.read());is.reset();is.skip(-1);assertEquals((int)'0',is.read());is.reset();byte[] bytes=new byte[2];int read=is.read(bytes,1,1);assertThat(read,is(1));is.reset();StreamUtils.skipUnusedStream(is);assertEquals(-1,is.read());is.close();}
}