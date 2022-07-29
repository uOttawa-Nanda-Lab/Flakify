/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.Test;

import static okio.Util.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class OkioTest {
  @Test public void sourceFromInputStream() throws Exception{InputStream in=new ByteArrayInputStream(("a" + repeat('b',Segment.SIZE * 2) + "c").getBytes(UTF_8));Source source=Okio.source(in);OkBuffer sink=new OkBuffer();assertEquals(3,source.read(sink,3));assertEquals("abb",sink.readUtf8(3));assertEquals(Segment.SIZE,source.read(sink,20000));assertEquals(repeat('b',Segment.SIZE),sink.readUtf8(sink.size()));assertEquals(Segment.SIZE - 1,source.read(sink,20000));assertEquals(repeat('b',Segment.SIZE - 2) + "c",sink.readUtf8(sink.size()));assertEquals(-1,source.read(sink,1));}

  private String repeat(char c, int count) {
    char[] array = new char[count];
    Arrays.fill(array, c);
    return new String(array);
  }
}
