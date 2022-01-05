/** 
 * Oldest entries are evicted to support newer ones. 
 */
@Test public void testEviction() throws IOException {
  OkBuffer out=new OkBuffer();
  out.writeByte(0x00);
  out.writeByte(0x0a);
  out.writeUtf8("custom-foo");
  out.writeByte(0x0d);
  out.writeUtf8("custom-header");
  out.writeByte(0x00);
  out.writeByte(0x0a);
  out.writeUtf8("custom-bar");
  out.writeByte(0x0d);
  out.writeUtf8("custom-header");
  out.writeByte(0x00);
  out.writeByte(0x0a);
  out.writeUtf8("custom-baz");
  out.writeByte(0x0d);
  out.writeUtf8("custom-header");
  bytesIn.write(out,out.size());
  hpackReader.maxHeaderTableByteCount(110);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(2,hpackReader.headerCount);
  Header entry=hpackReader.headerTable[headerTableLength() - 1];
  checkEntry(entry,"custom-bar","custom-header",55);
  assertHeaderReferenced(headerTableLength() - 1);
  entry=hpackReader.headerTable[headerTableLength() - 2];
  checkEntry(entry,"custom-baz","custom-header",55);
  assertHeaderReferenced(headerTableLength() - 2);
  assertEquals(headerEntries("custom-bar","custom-header","custom-baz","custom-header"),hpackReader.getAndReset());
  hpackReader.maxHeaderTableByteCount(55);
  assertEquals(1,hpackReader.headerCount);
}
