/*
 * Copyright (C) 2013 Square, Inc.
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
package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import okio.BufferedSource;
import okio.ByteString;
import okio.OkBuffer;
import org.junit.Test;

import static com.squareup.okhttp.internal.Util.headerEntries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Http20Draft09Test {
  static final int expectedStreamId = 15;

  @Test public void readRstStreamFrame() throws IOException{OkBuffer frame=new OkBuffer();frame.writeShort(4);frame.writeByte(Http20Draft09.TYPE_RST_STREAM);frame.writeByte(0);frame.writeInt(expectedStreamId & 0x7fffffff);frame.writeInt(ErrorCode.COMPRESSION_ERROR.httpCode);FrameReader fr=new Http20Draft09.Reader(frame,4096,false);fr.nextFrame(new BaseTestHandler(){@Override public void rstStream(int streamId,ErrorCode errorCode){assertEquals(expectedStreamId,streamId);assertEquals(ErrorCode.COMPRESSION_ERROR,errorCode);}});}

  private OkBuffer literalHeaders(List<Header> sentHeaders) throws IOException {
    OkBuffer out = new OkBuffer();
    new HpackDraft05.Writer(out).writeHeaders(sentHeaders);
    return out;
  }

  private OkBuffer sendPingFrame(boolean ack, int payload1, int payload2) throws IOException {
    OkBuffer out = new OkBuffer();
    new Http20Draft09.Writer(out, true).ping(ack, payload1, payload2);
    return out;
  }

  private OkBuffer sendGoAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData)
      throws IOException {
    OkBuffer out = new OkBuffer();
    new Http20Draft09.Writer(out, true).goAway(lastGoodStreamId, errorCode, debugData);
    return out;
  }

  private OkBuffer sendDataFrame(OkBuffer data) throws IOException {
    OkBuffer out = new OkBuffer();
    new Http20Draft09.Writer(out, true).dataFrame(expectedStreamId, Http20Draft09.FLAG_NONE, data,
        (int) data.size());
    return out;
  }

  private OkBuffer windowUpdate(long windowSizeIncrement) throws IOException {
    OkBuffer out = new OkBuffer();
    new Http20Draft09.Writer(out, true).windowUpdate(expectedStreamId, windowSizeIncrement);
    return out;
  }
}
