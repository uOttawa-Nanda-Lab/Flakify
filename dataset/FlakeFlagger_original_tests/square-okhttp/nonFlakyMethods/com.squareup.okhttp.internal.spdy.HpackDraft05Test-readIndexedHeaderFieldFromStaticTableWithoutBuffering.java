/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.1.4
 */
@Test public void readIndexedHeaderFieldFromStaticTableWithoutBuffering() throws IOException {
  bytesIn.writeByte(0x82);
  hpackReader.maxHeaderTableByteCount(0);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(0,hpackReader.headerCount);
  assertEquals(headerEntries(":method","GET"),hpackReader.getAndReset());
}
