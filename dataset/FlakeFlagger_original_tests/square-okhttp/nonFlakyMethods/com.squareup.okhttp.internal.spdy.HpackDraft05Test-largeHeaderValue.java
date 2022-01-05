/** 
 * Variable-length quantity special cases strings which are longer than 127 bytes.  Values such as cookies can be 4KiB, and should be possible to send. <p> http://tools.ietf.org/html/draft-ietf-httpbis-header-compression-05#section-4.1.2
 */
@Test public void largeHeaderValue() throws IOException {
  char[] value=new char[4096];
  Arrays.fill(value,'!');
  List<Header> headerBlock=headerEntries("cookie",new String(value));
  hpackWriter.writeHeaders(headerBlock);
  bytesIn.write(bytesOut,bytesOut.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(0,hpackReader.headerCount);
  assertEquals(headerBlock,hpackReader.getAndReset());
}
