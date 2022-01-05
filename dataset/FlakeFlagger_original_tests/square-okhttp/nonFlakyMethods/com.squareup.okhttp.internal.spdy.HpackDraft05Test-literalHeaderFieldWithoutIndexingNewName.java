/** 
 * Literal Header Field without Indexing - New Name
 */
@Test public void literalHeaderFieldWithoutIndexingNewName() throws IOException {
  List<Header> headerBlock=headerEntries("custom-key","custom-header");
  OkBuffer expectedBytes=new OkBuffer();
  expectedBytes.writeByte(0x40);
  expectedBytes.writeByte(0x0a);
  expectedBytes.write("custom-key".getBytes(),0,10);
  expectedBytes.writeByte(0x0d);
  expectedBytes.write("custom-header".getBytes(),0,13);
  hpackWriter.writeHeaders(headerBlock);
  assertEquals(expectedBytes,bytesOut);
  bytesIn.write(bytesOut,bytesOut.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(0,hpackReader.headerCount);
  assertEquals(headerBlock,hpackReader.getAndReset());
}
