/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ByteStringTest {

  private final String bronzeHorseman = "На берегу пустынных волн";

  @Test public void utf8() throws Exception{ByteString byteString=ByteString.encodeUtf8(bronzeHorseman);assertByteArraysEquals(byteString.toByteArray(),bronzeHorseman.getBytes(Util.UTF_8));assertTrue(byteString.equals(ByteString.of(bronzeHorseman.getBytes(Util.UTF_8))));assertEquals(byteString.utf8(),bronzeHorseman);}

  private static void assertByteArraysEquals(byte[] a, byte[] b) {
    assertEquals(Arrays.toString(a), Arrays.toString(b));
  }
}
