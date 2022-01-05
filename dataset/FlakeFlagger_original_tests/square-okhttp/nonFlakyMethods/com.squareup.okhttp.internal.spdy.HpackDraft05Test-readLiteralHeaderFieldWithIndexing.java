/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.1.1
 */
@Test public void readLiteralHeaderFieldWithIndexing() throws IOException {
  OkBuffer out=new OkBuffer();
  out.writeByte(0x00);
  out.writeByte(0x0a);
  out.writeUtf8("custom-key");
  out.writeByte(0x0d);
  out.writeUtf8("custom-header");
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(1,hpackReader.headerCount);
  assertEquals(55,hpackReader.headerTableByteCount);
  Header entry=hpackReader.headerTable[headerTableLength() - 1];
  checkEntry(entry,"custom-key","custom-header",55);
  assertHeaderReferenced(headerTableLength() - 1);
  assertEquals(headerEntries("custom-key","custom-header"),hpackReader.getAndReset());
}
