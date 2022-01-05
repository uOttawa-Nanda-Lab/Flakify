/** 
 * HPACK has a max header table size, which can be smaller than the max header message. Ensure the larger header content is not lost.
 */
@Test public void tooLargeToHPackIsStillEmitted() throws IOException {
  OkBuffer out=new OkBuffer();
  out.writeByte(0x00);
  out.writeByte(0x0a);
  out.writeUtf8("custom-key");
  out.writeByte(0x0d);
  out.writeUtf8("custom-header");
  bytesIn.write(out,out.size());
  hpackReader.maxHeaderTableByteCount(1);
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(0,hpackReader.headerCount);
  assertEquals(headerEntries("custom-key","custom-header"),hpackReader.getAndReset());
}
