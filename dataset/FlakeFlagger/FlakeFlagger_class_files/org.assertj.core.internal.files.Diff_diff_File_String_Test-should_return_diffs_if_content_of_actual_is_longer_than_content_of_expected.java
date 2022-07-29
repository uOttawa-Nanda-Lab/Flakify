/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.internal.files;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.util.Arrays.array;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.assertj.core.internal.Diff;
import org.assertj.core.util.TextFileWriter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for <code>{@link Diff#diff(File, String, java.nio.charset.Charset)}</code>.
 * 
 * @author Olivier Michallat
 */
public class Diff_diff_File_String_Test {
  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private static Diff diff;
  private static TextFileWriter writer;

  @BeforeClass
  public static void setUpOnce() {
    diff = new Diff();
    writer = TextFileWriter.instance();
  }

  private File actual;

  @Before
  public void setUp() throws IOException {
    actual = folder.newFile("actual.txt");
  }

  @Test public void should_return_diffs_if_content_of_actual_is_longer_than_content_of_expected() throws IOException{writer.write(actual,"line_0","line_1");String expected="line_0";List<String> diffs=diff.diff(actual,expected,Charset.defaultCharset());assertEquals(1,diffs.size());assertEquals("line:<2>, expected:<EOF> but was:<line_1>",diffs.get(0));}
}
