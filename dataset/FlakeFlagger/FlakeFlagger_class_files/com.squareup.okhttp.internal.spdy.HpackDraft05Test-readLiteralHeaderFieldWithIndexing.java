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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import okio.ByteString;
import okio.OkBuffer;
import org.junit.Before;
import org.junit.Test;

import static com.squareup.okhttp.internal.Util.headerEntries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HpackDraft05Test {

  private final OkBuffer bytesIn = new OkBuffer();
  private HpackDraft05.Reader hpackReader;
  private OkBuffer bytesOut = new OkBuffer();
  private HpackDraft05.Writer hpackWriter;

  @Before public void reset() {
    hpackReader = newReader(bytesIn);
    hpackWriter = new HpackDraft05.Writer(bytesOut);
  }

  /**
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.1.1
 */@Test public void readLiteralHeaderFieldWithIndexing() throws IOException{OkBuffer out=new OkBuffer();out.writeByte(0x00);out.writeByte(0x0a);out.writeUtf8("custom-key");out.writeByte(0x0d);out.writeUtf8("custom-header");bytesIn.write(out,out.size());hpackReader.readHeaders();hpackReader.emitReferenceSet();assertEquals(1,hpackReader.headerCount);assertEquals(55,hpackReader.headerTableByteCount);Header entry=hpackReader.headerTable[headerTableLength() - 1];checkEntry(entry,"custom-key","custom-header",55);assertHeaderReferenced(headerTableLength() - 1);assertEquals(headerEntries("custom-key","custom-header"),hpackReader.getAndReset());}

  private OkBuffer firstRequestWithoutHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x82); // == Indexed - Add ==
                         // idx = 2 -> :method: GET
    out.writeByte(0x87); // == Indexed - Add ==
                         // idx = 7 -> :scheme: http
    out.writeByte(0x86); // == Indexed - Add ==
                         // idx = 6 -> :path: /
    out.writeByte(0x04); // == Literal indexed ==
                         // Indexed name (idx = 4) -> :authority
    out.writeByte(0x0f); // Literal value (len = 15)
    out.writeUtf8("www.example.com");

    return out;
  }

  private void checkReadFirstRequestWithoutHuffman() {
    assertEquals(4, hpackReader.headerCount);

    // [  1] (s =  57) :authority: www.example.com
    Header entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  2] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderReferenced(headerTableLength() - 3);

    // [  3] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderReferenced(headerTableLength() - 2);

    // [  4] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 180
    assertEquals(180, hpackReader.headerTableByteCount);

    // Decoded header set:
    assertEquals(headerEntries(
        ":method", "GET",
        ":scheme", "http",
        ":path", "/",
        ":authority", "www.example.com"), hpackReader.getAndReset());
  }

  private OkBuffer secondRequestWithoutHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x1b); // == Literal indexed ==
                         // Indexed name (idx = 27) -> cache-control
    out.writeByte(0x08); // Literal value (len = 8)
    out.writeUtf8("no-cache");

    return out;
  }

  private void checkReadSecondRequestWithoutHuffman() {
    assertEquals(5, hpackReader.headerCount);

    // [  1] (s =  53) cache-control: no-cache
    Header entry = hpackReader.headerTable[headerTableLength() - 5];
    checkEntry(entry, "cache-control", "no-cache", 53);
    assertHeaderReferenced(headerTableLength() - 5);

    // [  2] (s =  57) :authority: www.example.com
    entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  3] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderReferenced(headerTableLength() - 3);

    // [  4] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderReferenced(headerTableLength() - 2);

    // [  5] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 233
    assertEquals(233, hpackReader.headerTableByteCount);

    // Decoded header set:
    assertEquals(headerEntries(
        ":method", "GET",
        ":scheme", "http",
        ":path", "/",
        ":authority", "www.example.com",
        "cache-control", "no-cache"), hpackReader.getAndReset());
  }

  private OkBuffer thirdRequestWithoutHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x80); // == Empty reference set ==
    out.writeByte(0x85); // == Indexed - Add ==
                         // idx = 5 -> :method: GET
    out.writeByte(0x8c); // == Indexed - Add ==
                         // idx = 12 -> :scheme: https
    out.writeByte(0x8b); // == Indexed - Add ==
                         // idx = 11 -> :path: /index.html
    out.writeByte(0x84); // == Indexed - Add ==
                         // idx = 4 -> :authority: www.example.com
    out.writeByte(0x00); // Literal indexed
    out.writeByte(0x0a); // Literal name (len = 10)
    out.writeUtf8("custom-key");
    out.writeByte(0x0c); // Literal value (len = 12)
    out.writeUtf8("custom-value");

    return out;
  }

  private void checkReadThirdRequestWithoutHuffman() {
    assertEquals(8, hpackReader.headerCount);

    // [  1] (s =  54) custom-key: custom-value
    Header entry = hpackReader.headerTable[headerTableLength() - 8];
    checkEntry(entry, "custom-key", "custom-value", 54);
    assertHeaderReferenced(headerTableLength() - 8);

    // [  2] (s =  48) :path: /index.html
    entry = hpackReader.headerTable[headerTableLength() - 7];
    checkEntry(entry, ":path", "/index.html", 48);
    assertHeaderReferenced(headerTableLength() - 7);

    // [  3] (s =  44) :scheme: https
    entry = hpackReader.headerTable[headerTableLength() - 6];
    checkEntry(entry, ":scheme", "https", 44);
    assertHeaderReferenced(headerTableLength() - 6);

    // [  4] (s =  53) cache-control: no-cache
    entry = hpackReader.headerTable[headerTableLength() - 5];
    checkEntry(entry, "cache-control", "no-cache", 53);
    assertHeaderNotReferenced(headerTableLength() - 5);

    // [  5] (s =  57) :authority: www.example.com
    entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  6] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderNotReferenced(headerTableLength() - 3);

    // [  7] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderNotReferenced(headerTableLength() - 2);

    // [  8] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 379
    assertEquals(379, hpackReader.headerTableByteCount);

    // Decoded header set:
    // TODO: order is not correct per docs, but then again, the spec doesn't require ordering.
    assertEquals(headerEntries(
        ":method", "GET",
        ":authority", "www.example.com",
        ":scheme", "https",
        ":path", "/index.html",
        "custom-key", "custom-value"), hpackReader.getAndReset());
  }

  private OkBuffer firstRequestWithHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x82); // == Indexed - Add ==
                         // idx = 2 -> :method: GET
    out.writeByte(0x87); // == Indexed - Add ==
                         // idx = 7 -> :scheme: http
    out.writeByte(0x86); // == Indexed - Add ==
                         // idx = 6 -> :path: /
    out.writeByte(0x04); // == Literal indexed ==
                         // Indexed name (idx = 4) -> :authority
    out.writeByte(0x8b); // Literal value Huffman encoded 11 bytes
                         // decodes to www.example.com which is length 15
    byte[] huffmanBytes = new byte[] {
        (byte) 0xdb, (byte) 0x6d, (byte) 0x88, (byte) 0x3e,
        (byte) 0x68, (byte) 0xd1, (byte) 0xcb, (byte) 0x12,
        (byte) 0x25, (byte) 0xba, (byte) 0x7f};
    out.write(huffmanBytes, 0, huffmanBytes.length);

    return out;
  }

  private void checkReadFirstRequestWithHuffman() {
    assertEquals(4, hpackReader.headerCount);

    // [  1] (s =  57) :authority: www.example.com
    Header entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  2] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderReferenced(headerTableLength() - 3);

    // [  3] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderReferenced(headerTableLength() - 2);

    // [  4] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 180
    assertEquals(180, hpackReader.headerTableByteCount);

    // Decoded header set:
    assertEquals(headerEntries(
        ":method", "GET",
        ":scheme", "http",
        ":path", "/",
        ":authority", "www.example.com"), hpackReader.getAndReset());
  }

  private OkBuffer secondRequestWithHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x1b); // == Literal indexed ==
                         // Indexed name (idx = 27) -> cache-control
    out.writeByte(0x86); // Literal value Huffman encoded 6 bytes
                         // decodes to no-cache which is length 8
    byte[] huffmanBytes = new byte[] {
        (byte) 0x63, (byte) 0x65, (byte) 0x4a, (byte) 0x13,
        (byte) 0x98, (byte) 0xff};
    out.write(huffmanBytes, 0, huffmanBytes.length);

    return out;
  }

  private void checkReadSecondRequestWithHuffman() {
    assertEquals(5, hpackReader.headerCount);

    // [  1] (s =  53) cache-control: no-cache
    Header entry = hpackReader.headerTable[headerTableLength() - 5];
    checkEntry(entry, "cache-control", "no-cache", 53);
    assertHeaderReferenced(headerTableLength() - 5);

    // [  2] (s =  57) :authority: www.example.com
    entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  3] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderReferenced(headerTableLength() - 3);

    // [  4] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderReferenced(headerTableLength() - 2);

    // [  5] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 233
    assertEquals(233, hpackReader.headerTableByteCount);

    // Decoded header set:
    assertEquals(headerEntries(
        ":method", "GET",
        ":scheme", "http",
        ":path", "/",
        ":authority", "www.example.com",
        "cache-control", "no-cache"), hpackReader.getAndReset());
  }

  private OkBuffer thirdRequestWithHuffman() {
    OkBuffer out = new OkBuffer();

    out.writeByte(0x80); // == Empty reference set ==
    out.writeByte(0x85); // == Indexed - Add ==
                         // idx = 5 -> :method: GET
    out.writeByte(0x8c); // == Indexed - Add ==
                         // idx = 12 -> :scheme: https
    out.writeByte(0x8b); // == Indexed - Add ==
                         // idx = 11 -> :path: /index.html
    out.writeByte(0x84); // == Indexed - Add ==
                         // idx = 4 -> :authority: www.example.com
    out.writeByte(0x00); // Literal indexed
    out.writeByte(0x88); // Literal name Huffman encoded 8 bytes
                         // decodes to custom-key which is length 10
    byte[] huffmanBytes = new byte[] {
        (byte) 0x4e, (byte) 0xb0, (byte) 0x8b, (byte) 0x74,
        (byte) 0x97, (byte) 0x90, (byte) 0xfa, (byte) 0x7f};
    out.write(huffmanBytes, 0, huffmanBytes.length);
    out.writeByte(0x89); // Literal value Huffman encoded 6 bytes
                         // decodes to custom-value which is length 12
    huffmanBytes = new byte[] {
        (byte) 0x4e, (byte) 0xb0, (byte) 0x8b, (byte) 0x74,
        (byte) 0x97, (byte) 0x9a, (byte) 0x17, (byte) 0xa8,
        (byte) 0xff};
    out.write(huffmanBytes, 0, huffmanBytes.length);

    return out;
  }

  private void checkReadThirdRequestWithHuffman() {
    assertEquals(8, hpackReader.headerCount);

    // [  1] (s =  54) custom-key: custom-value
    Header entry = hpackReader.headerTable[headerTableLength() - 8];
    checkEntry(entry, "custom-key", "custom-value", 54);
    assertHeaderReferenced(headerTableLength() - 8);

    // [  2] (s =  48) :path: /index.html
    entry = hpackReader.headerTable[headerTableLength() - 7];
    checkEntry(entry, ":path", "/index.html", 48);
    assertHeaderReferenced(headerTableLength() - 7);

    // [  3] (s =  44) :scheme: https
    entry = hpackReader.headerTable[headerTableLength() - 6];
    checkEntry(entry, ":scheme", "https", 44);
    assertHeaderReferenced(headerTableLength() - 6);

    // [  4] (s =  53) cache-control: no-cache
    entry = hpackReader.headerTable[headerTableLength() - 5];
    checkEntry(entry, "cache-control", "no-cache", 53);
    assertHeaderNotReferenced(headerTableLength() - 5);

    // [  5] (s =  57) :authority: www.example.com
    entry = hpackReader.headerTable[headerTableLength() - 4];
    checkEntry(entry, ":authority", "www.example.com", 57);
    assertHeaderReferenced(headerTableLength() - 4);

    // [  6] (s =  38) :path: /
    entry = hpackReader.headerTable[headerTableLength() - 3];
    checkEntry(entry, ":path", "/", 38);
    assertHeaderNotReferenced(headerTableLength() - 3);

    // [  7] (s =  43) :scheme: http
    entry = hpackReader.headerTable[headerTableLength() - 2];
    checkEntry(entry, ":scheme", "http", 43);
    assertHeaderNotReferenced(headerTableLength() - 2);

    // [  8] (s =  42) :method: GET
    entry = hpackReader.headerTable[headerTableLength() - 1];
    checkEntry(entry, ":method", "GET", 42);
    assertHeaderReferenced(headerTableLength() - 1);

    // Table size: 379
    assertEquals(379, hpackReader.headerTableByteCount);

    // Decoded header set:
    // TODO: order is not correct per docs, but then again, the spec doesn't require ordering.
    assertEquals(headerEntries(
        ":method", "GET",
        ":authority", "www.example.com",
        ":scheme", "https",
        ":path", "/index.html",
        "custom-key", "custom-value"), hpackReader.getAndReset());
  }

  private HpackDraft05.Reader newReader(OkBuffer source) {
    return new HpackDraft05.Reader(false, 4096, source);
  }

  private OkBuffer byteStream(int... bytes) {
    return new OkBuffer().write(intArrayToByteArray(bytes));
  }

  private void checkEntry(Header entry, String name, String value, int size) {
    assertEquals(name, entry.name.utf8());
    assertEquals(value, entry.value.utf8());
    assertEquals(size, entry.hpackSize);
  }

  private void assertBytes(int... bytes) {
    ByteString expected = intArrayToByteArray(bytes);
    ByteString actual = bytesOut.readByteString(bytesOut.size());
    assertEquals(expected, actual);
  }

  private ByteString intArrayToByteArray(int[] bytes) {
    byte[] data = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      data[i] = (byte) bytes[i];
    }
    return ByteString.of(data);
  }

  private void assertHeaderReferenced(int index) {
    assertTrue(hpackReader.referencedHeaders.get(index));
  }

  private void assertHeaderNotReferenced(int index) {
    assertFalse(hpackReader.referencedHeaders.get(index));
  }

  private int headerTableLength() {
    return hpackReader.headerTable.length;
  }
}
