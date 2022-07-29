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
import java.util.Arrays;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class InflaterSourceTest {
  @Test public void inflatePoorlyCompressed() throws Exception{ByteString original=randomBytes(1024 * 1024);OkBuffer deflated=deflate(original);OkBuffer inflated=inflate(deflated);assertEquals(original,inflated.readByteString(inflated.size()));}

  private OkBuffer decodeBase64(String s) {
    return new OkBuffer().write(ByteString.decodeBase64(s));
  }

  private String readUtf8(OkBuffer buffer) {
    return buffer.readUtf8(buffer.size());
  }

  /** Use DeflaterOutputStream to deflate source. */
  private OkBuffer deflate(ByteString source) throws IOException {
    OkBuffer result = new OkBuffer();
    Sink sink = Okio.sink(new DeflaterOutputStream(result.outputStream()));
    sink.write(new OkBuffer().write(source), source.size());
    sink.close();
    return result;
  }

  private ByteString randomBytes(int length) {
    Random random = new Random(0);
    byte[] randomBytes = new byte[length];
    random.nextBytes(randomBytes);
    return ByteString.of(randomBytes);
  }

  private String repeat(char c, int count) {
    char[] array = new char[count];
    Arrays.fill(array, c);
    return new String(array);
  }
}
