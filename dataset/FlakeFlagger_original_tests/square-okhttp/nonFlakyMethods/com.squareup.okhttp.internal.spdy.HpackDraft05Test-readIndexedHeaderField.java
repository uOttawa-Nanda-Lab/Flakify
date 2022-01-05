/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.1.3
 */
@Test public void readIndexedHeaderField() throws IOException {
  bytesIn.writeByte(0x82);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(1,hpackReader.headerCount);
  assertEquals(42,hpackReader.headerTableByteCount);
  Header entry=hpackReader.headerTable[headerTableLength() - 1];
  checkEntry(entry,":method","GET",42);
  assertHeaderReferenced(headerTableLength() - 1);
  assertEquals(headerEntries(":method","GET"),hpackReader.getAndReset());
}
