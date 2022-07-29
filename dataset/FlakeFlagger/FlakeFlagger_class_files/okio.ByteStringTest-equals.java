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

  @Test public void equals() throws Exception {
    ByteString byteString = ByteString.decodeHex("000102");
    assertTrue(byteString.equals(byteString));
    assertTrue(byteString.equals(ByteString.decodeHex("000102")));
    assertTrue(ByteString.of().equals(ByteString.EMPTY));
    assertTrue(ByteString.EMPTY.equals(ByteString.of()));
    assertFalse(byteString.equals(new Object()));
    assertFalse(byteString.equals(ByteString.decodeHex("000201")));
  }

  private final String bronzeHorseman = "На берегу пустынных волн";

  private static void assertByteArraysEquals(byte[] a, byte[] b) {
    assertEquals(Arrays.toString(a), Arrays.toString(b));
  }
}
