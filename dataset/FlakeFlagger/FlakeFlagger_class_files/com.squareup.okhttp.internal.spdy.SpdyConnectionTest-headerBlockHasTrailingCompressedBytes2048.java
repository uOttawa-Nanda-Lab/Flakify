/*
 * Copyright (C) 2011 The Android Open Source Project
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
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.OkBuffer;
import okio.Okio;
import okio.Source;
import org.junit.After;
import org.junit.Test;

import static com.squareup.okhttp.internal.Util.headerEntries;
import static com.squareup.okhttp.internal.spdy.ErrorCode.CANCEL;
import static com.squareup.okhttp.internal.spdy.ErrorCode.INTERNAL_ERROR;
import static com.squareup.okhttp.internal.spdy.ErrorCode.INVALID_STREAM;
import static com.squareup.okhttp.internal.spdy.ErrorCode.PROTOCOL_ERROR;
import static com.squareup.okhttp.internal.spdy.ErrorCode.REFUSED_STREAM;
import static com.squareup.okhttp.internal.spdy.ErrorCode.STREAM_IN_USE;
import static com.squareup.okhttp.internal.spdy.Settings.PERSIST_VALUE;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_DATA;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_GOAWAY;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_HEADERS;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_PING;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_RST_STREAM;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_SETTINGS;
import static com.squareup.okhttp.internal.spdy.Spdy3.TYPE_WINDOW_UPDATE;
import static com.squareup.okhttp.internal.spdy.SpdyConnection.INITIAL_WINDOW_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public final class SpdyConnectionTest {
  private static final Variant SPDY3 = new Spdy3();
  private static final Variant HTTP_20_DRAFT_09 = new Http20Draft09();
  private final MockSpdyPeer peer = new MockSpdyPeer();

  private OkBuffer data(int byteCount) {
    return new OkBuffer().write(new byte[byteCount]);
  }

  @Test public void headerBlockHasTrailingCompressedBytes2048() throws Exception{String frame="gAMAAgAAB/sAAAABeLvjxqfCAqYjRhAGJmxGxUQAAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAA" + "AAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP/" + "/SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQ" + "AAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD" + "//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0o" + "EAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAA" + "A//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9" + "KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAA" + "AAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP/" + "/SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQ" + "AAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD" + "//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0o" + "EAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAA" + "A//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9" + "KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAA" + "AAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP/" + "/SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQ" + "AAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD" + "//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0o" + "EAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAA" + "A//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9" + "KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAA" + "AAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP/" + "/SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQ" + "AAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD" + "//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0o" + "EAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAA" + "A//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9" + "KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAA" + "AAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP/" + "/SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQAAAD//0oEAAAA//9KBAAAAP//SgQ" + "AAAD//0oEAAAA//8=";headerBlockHasTrailingCompressedBytes(frame,289);}

  private void headerBlockHasTrailingCompressedBytes(String frame, int length) throws IOException {
    // write the mocking script
    peer.acceptFrame(); // SYN_STREAM
    peer.sendFrame(ByteString.decodeBase64(frame).toByteArray());
    peer.sendFrame().data(true, 1, new OkBuffer().writeUtf8("robot"));
    peer.acceptFrame(); // DATA
    peer.play();

    // play it back
    SpdyConnection connection = connection(peer, SPDY3);
    SpdyStream stream = connection.newStream(headerEntries("b", "banana"), true, true);
    assertEquals("a", stream.getResponseHeaders().get(0).name.utf8());
    assertEquals(length, stream.getResponseHeaders().get(0).value.size());
    assertStreamData("robot", stream.getSource());
  }

  private SpdyConnection sendHttp2SettingsAndCheckForAck(boolean client, Settings settings)
      throws IOException, InterruptedException {
    peer.setVariantAndClient(HTTP_20_DRAFT_09, client);
    peer.sendFrame().settings(settings);
    peer.acceptFrame(); // ACK
    peer.play();

    // play it back
    SpdyConnection connection = connection(peer, HTTP_20_DRAFT_09);

    // verify the peer received the ACK
    MockSpdyPeer.InFrame ackFrame = peer.takeFrame();
    assertEquals(TYPE_SETTINGS, ackFrame.type);
    assertEquals(0, ackFrame.streamId);
    assertTrue(ackFrame.ack);
    return connection;
  }

  private SpdyConnection connection(MockSpdyPeer peer, Variant variant) throws IOException {
    return connectionBuilder(peer, variant).build();
  }

  private SpdyConnection.Builder connectionBuilder(MockSpdyPeer peer, Variant variant)
      throws IOException {
    return new SpdyConnection.Builder(true, peer.openSocket())
        .pushObserver(IGNORE)
        .protocol(variant.getProtocol());
  }

  private void assertStreamData(String expected, Source source) throws IOException {
    OkBuffer buffer = new OkBuffer();
    while (source.read(buffer, Long.MAX_VALUE) != -1) {
    }
    String actual = buffer.readUtf8(buffer.size());
    assertEquals(expected, actual);
  }

  private void assertFlushBlocks(BufferedSink out) throws IOException {
    interruptAfterDelay(500);
    try {
      out.flush();
      fail();
    } catch (InterruptedIOException expected) {
    }
  }

  /** Interrupts the current thread after {@code delayMillis}. */
  private void interruptAfterDelay(final long delayMillis) {
    final Thread toInterrupt = Thread.currentThread();
    new Thread("interrupting cow") {
      @Override public void run() {
        try {
          Thread.sleep(delayMillis);
          toInterrupt.interrupt();
        } catch (InterruptedException e) {
          throw new AssertionError();
        }
      }
    }.start();
  }

  static int roundUp(int num, int divisor) {
    return (num + divisor - 1) / divisor;
  }

  static final PushObserver IGNORE = new PushObserver() {

    @Override public boolean onRequest(int streamId, List<Header> requestHeaders) {
      return false;
    }

    @Override public boolean onHeaders(int streamId, List<Header> responseHeaders, boolean last) {
      return false;
    }

    @Override public boolean onData(int streamId, BufferedSource source, int byteCount,
        boolean last) throws IOException {
      source.skip(byteCount);
      return false;
    }

    @Override public void onReset(int streamId, ErrorCode errorCode) {
    }
  };
}
