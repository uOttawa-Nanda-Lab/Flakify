/** 
 * Header table backing array is initially 8 long, let's ensure it grows. 
 */
@Test public void dynamicallyGrowsBeyond64Entries() throws IOException {
  OkBuffer out=new OkBuffer();
  for (int i=0; i < 256; i++) {
    out.writeByte(0x00);
    out.writeByte(0x0a);
    out.writeUtf8("custom-foo");
    out.writeByte(0x0d);
    out.writeUtf8("custom-header");
  }
  bytesIn.write(out,out.size());
  hpackReader.maxHeaderTableByteCount(16384);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(256,hpackReader.headerCount);
  assertHeaderReferenced(headerTableLength() - 1);
  assertHeaderReferenced(headerTableLength() - hpackReader.headerCount);
}
