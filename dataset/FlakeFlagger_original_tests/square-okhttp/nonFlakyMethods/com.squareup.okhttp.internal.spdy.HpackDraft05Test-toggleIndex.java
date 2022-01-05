/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#section-3.2.1
 */
@Test public void toggleIndex() throws IOException {
  bytesIn.writeByte(0x82);
  bytesIn.writeByte(0x81);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(1,hpackReader.headerCount);
  assertEquals(42,hpackReader.headerTableByteCount);
  Header entry=hpackReader.headerTable[headerTableLength() - 1];
  checkEntry(entry,":method","GET",42);
  assertHeaderNotReferenced(headerTableLength() - 1);
  assertTrue(hpackReader.getAndReset().isEmpty());
}
