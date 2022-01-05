@Test public void prefixMask() throws IOException {
  hpackWriter.writeInt(31,31,0);
  assertBytes(31,0);
  assertEquals(31,newReader(byteStream(0)).readInt(31,31));
}
