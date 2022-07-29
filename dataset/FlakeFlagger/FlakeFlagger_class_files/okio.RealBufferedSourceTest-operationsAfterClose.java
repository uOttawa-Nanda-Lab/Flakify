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
  @Test public void operationsAfterClose() throws IOException {
    OkBuffer source = new OkBuffer();
    BufferedSource bufferedSource = new RealBufferedSource(source);
    bufferedSource.close();

    // Test a sample set of methods.
    try {
      bufferedSource.seek((byte) 1);
      fail();
    } catch (IllegalStateException expected) {
    }

    try {
      bufferedSource.skip(1);
      fail();
    } catch (IllegalStateException expected) {
    }

    try {
      bufferedSource.readByte();
      fail();
    } catch (IllegalStateException expected) {
    }

    try {
      bufferedSource.readByteString(10);
      fail();
    } catch (IllegalStateException expected) {
    }

    // Test a sample set of methods on the InputStream.
    InputStream is = bufferedSource.inputStream();
    try {
      is.read();
      fail();
    } catch (IOException expected) {
    }

    try {
      is.read(new byte[10]);
      fail();
    } catch (IOException expected) {
    }
  }

  private String repeat(char c, int count) {
    char[] array = new char[count];
    Arrays.fill(array, c);
    return new String(array);
  }
}
