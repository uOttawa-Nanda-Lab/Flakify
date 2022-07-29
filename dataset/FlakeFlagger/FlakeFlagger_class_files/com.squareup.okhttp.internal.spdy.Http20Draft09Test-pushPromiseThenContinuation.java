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

  /**
 * Headers are compressed, then framed. 
 */@Test public void pushPromiseThenContinuation() throws IOException{OkBuffer frame=new OkBuffer();final int expectedPromisedStreamId=11;final List<Header> pushPromise=Arrays.asList(new Header(Header.TARGET_METHOD,"GET"),new Header(Header.TARGET_SCHEME,"https"),new Header(Header.TARGET_AUTHORITY,"squareup.com"),new Header(Header.TARGET_PATH,"/"));OkBuffer headerBlock=literalHeaders(pushPromise);int firstFrameLength=(int)(headerBlock.size() - 1);{frame.writeShort(firstFrameLength + 4);frame.writeByte(Http20Draft09.TYPE_PUSH_PROMISE);frame.writeByte(0);frame.writeInt(expectedStreamId & 0x7fffffff);frame.writeInt(expectedPromisedStreamId & 0x7fffffff);frame.write(headerBlock,firstFrameLength);}{frame.writeShort(1);frame.writeByte(Http20Draft09.TYPE_CONTINUATION);frame.writeByte(Http20Draft09.FLAG_END_HEADERS);frame.writeInt(expectedStreamId & 0x7fffffff);frame.write(headerBlock,1);}FrameReader fr=new Http20Draft09.Reader(frame,4096,false);fr.nextFrame(new BaseTestHandler(){@Override public void pushPromise(int streamId,int promisedStreamId,List<Header> headerBlock){assertEquals(expectedStreamId,streamId);assertEquals(expectedPromisedStreamId,promisedStreamId);assertEquals(pushPromise,headerBlock);}});}

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
