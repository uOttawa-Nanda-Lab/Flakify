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
package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import okio.ByteString;
import okio.OkBuffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Spdy3Test {
  static final int expectedStreamId = 15;

  @Test public void goAwayRoundTrip() throws IOException{OkBuffer frame=new OkBuffer();final ErrorCode expectedError=ErrorCode.PROTOCOL_ERROR;frame.writeInt(0x80000000 | (Spdy3.VERSION & 0x7fff) << 16 | Spdy3.TYPE_GOAWAY & 0xffff);frame.writeInt(8);frame.writeInt(expectedStreamId);frame.writeInt(expectedError.spdyGoAwayCode);assertEquals(frame,sendGoAway(expectedStreamId,expectedError,Util.EMPTY_BYTE_ARRAY));assertEquals(frame,sendGoAway(expectedStreamId,expectedError,new byte[8]));FrameReader fr=new Spdy3.Reader(frame,false);fr.nextFrame(new BaseTestHandler(){@Override public void goAway(int lastGoodStreamId,ErrorCode errorCode,ByteString debugData){assertEquals(expectedStreamId,lastGoodStreamId);assertEquals(expectedError,errorCode);assertEquals(0,debugData.size());}});}

  private void sendDataFrame(OkBuffer source) throws IOException {
    Spdy3.Writer writer = new Spdy3.Writer(new OkBuffer(), true);
    writer.sendDataFrame(expectedStreamId, 0, source, (int) source.size());
  }

  private void windowUpdate(long increment) throws IOException {
    new Spdy3.Writer(new OkBuffer(), true).windowUpdate(expectedStreamId, increment);
  }

  private OkBuffer sendGoAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData)
      throws IOException {
    OkBuffer out = new OkBuffer();
    new Spdy3.Writer(out, true).goAway(lastGoodStreamId, errorCode, debugData);
    return out;
  }
}
