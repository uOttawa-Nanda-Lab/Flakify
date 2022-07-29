/*
 * Copyright (C) 2013 Square, Inc.
 * Copyright (C) 2011 The Guava Authors
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
package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test MediaType API and parsing.
 *
 * <p>This test includes tests from <a
 * href="https://code.google.com/p/guava-libraries/">Guava's</a> MediaTypeTest.
 */
public class MediaTypeTest {
  @Test public void testMultipleCharsets(){try {MediaType.parse("text/plain; charset=utf-8; charset=utf-16");fail();} catch (IllegalArgumentException expected){}}

  private void assertMediaType(String string) {
    MediaType mediaType = MediaType.parse(string);
    assertEquals(string, mediaType.toString());
  }

  private void assertInvalid(String string) {
    assertNull(string, MediaType.parse(string));
  }
}
