@Test public void huffmanDecodingSupported() throws IOException {
  OkBuffer out=new OkBuffer();
  out.writeByte(0x04);
  out.writeByte(0x8b);
  byte[] huffmanBytes=new byte[]{(byte)0xdb,(byte)0x6d,(byte)0x88,(byte)0x3e,(byte)0x68,(byte)0xd1,(byte)0xcb,(byte)0x12,(byte)0x25,(byte)0xba,(byte)0x7f};
  out.write(huffmanBytes,0,huffmanBytes.length);
  bytesIn.write(out,out.size());
  hpackReader.readHeaders();
  hpackReader.emitReferenceSet();
  assertEquals(1,hpackReader.headerCount);
  assertEquals(52,hpackReader.headerTableByteCount);
  Header entry=hpackReader.headerTable[headerTableLength() - 1];
  checkEntry(entry,":path","www.example.com",52);
  assertHeaderReferenced(headerTableLength() - 1);
}
