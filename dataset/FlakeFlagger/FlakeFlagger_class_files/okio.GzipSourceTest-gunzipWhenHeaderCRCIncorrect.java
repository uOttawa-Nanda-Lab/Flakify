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

import java.io.IOException;
import java.util.zip.CRC32;
import org.junit.Test;

import static okio.Util.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GzipSourceTest {

  private void assertGzipped(OkBuffer gzipped) throws IOException {
    OkBuffer gunzipped = gunzip(gzipped);
    assertEquals("It's a UNIX system! I know this!", gunzipped.readUtf8(gunzipped.size()));
  }

  /**
 * Note that you cannot test this with old versions of gzip, as they interpret flag bit 1 as CONTINUATION, not HCRC. For example, this is the case with the default gzip on osx.
 */@Test public void gunzipWhenHeaderCRCIncorrect() throws Exception{OkBuffer gzipped=new OkBuffer();gzipped.write(gzipHeaderWithFlags((byte)0x02));gzipped.writeShort((short)0);gzipped.write(deflated);gzipped.write(gzipTrailer);try {gunzip(gzipped);fail();} catch (IOException e){assertEquals("FHCRC: actual 0x0000261d != expected 0x00000000",e.getMessage());}}

  private ByteString gzipHeaderWithFlags(byte flags) {
    byte[] result = gzipHeader.toByteArray();
    result[3] = flags;
    return ByteString.of(result);
  }

  private final ByteString gzipHeader = ByteString.decodeHex("1f8b0800000000000000");

  // Deflated "It's a UNIX system! I know this!"
  private final ByteString deflated = ByteString.decodeHex(
      "f32c512f56485408f5f38c5028ae2c2e49cd5554f054c8cecb2f5728c9c82c560400");

  private final ByteString gzipTrailer = ByteString.decodeHex(""
      + "8d8fad37" // Checksum of deflated.
      + "20000000" // 32 in little endian.
  );

  /** This source keeps track of whether its read have returned -1. */
  static class ExhaustableSource implements Source {
    private final Source source;
    private boolean exhausted;

    ExhaustableSource(Source source) {
      this.source = source;
    }

    @Override public long read(OkBuffer sink, long byteCount) throws IOException {
      long result = source.read(sink, byteCount);
      if (result == -1) exhausted = true;
      return result;
    }

    @Override public Source deadline(Deadline deadline) {
      source.deadline(deadline);
      return this;
    }

    @Override public void close() throws IOException {
      source.close();
    }
  }
}
