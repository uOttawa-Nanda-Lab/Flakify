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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.junit.Test;

import static okio.Util.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class RealBufferedSourceTest {
  @Test public void inputStreamFromSource() throws Exception{OkBuffer source=new OkBuffer();source.writeUtf8("a");source.writeUtf8(repeat('b',Segment.SIZE));source.writeUtf8("c");InputStream in=new RealBufferedSource(source).inputStream();assertEquals(0,in.available());assertEquals(Segment.SIZE + 2,source.size());assertEquals('a',in.read());assertEquals(Segment.SIZE - 1,in.available());assertEquals(2,source.size());byte[] data=new byte[Segment.SIZE * 2];assertEquals(Segment.SIZE - 1,in.read(data,0,data.length));assertEquals(repeat('b',Segment.SIZE - 1),new String(data,0,Segment.SIZE - 1,UTF_8));assertEquals(2,source.size());assertEquals('b',in.read());assertEquals(1,in.available());assertEquals(0,source.size());assertEquals('c',in.read());assertEquals(0,in.available());assertEquals(0,source.size());assertEquals(-1,in.read());assertEquals(0,source.size());}

  private String repeat(char c, int count) {
    char[] array = new char[count];
    Arrays.fill(array, c);
    return new String(array);
  }
}
