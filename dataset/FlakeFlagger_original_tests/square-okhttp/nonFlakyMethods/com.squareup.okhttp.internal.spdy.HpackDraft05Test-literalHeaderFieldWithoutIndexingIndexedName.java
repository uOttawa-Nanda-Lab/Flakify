/** 
 * http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#appendix-E.1.2
 */
@Test public void literalHeaderFieldWithoutIndexingIndexedName() throws IOException {
  List<Header> headerBlock=headerEntries(":path","/sample/path");
  OkBuffer expectedBytes=new OkBuffer();
  expectedBytes.writeByte(0x44);
  expectedBytes.writeByte(0x0c);
  expectedBytes.write("/sample/path".getBytes(),0,12);
  hpackWriter.writeHeaders(headerBlock);
  assertEquals(expectedBytes,bytesOut);
  bytesIn.write(bytesOut,bytesOut.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(0,hpackReader.headerCount);
  assertEquals(headerBlock,hpackReader.getAndReset());
}
