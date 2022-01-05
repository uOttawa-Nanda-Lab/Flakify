@Test public void prefixMaskMinusOne() throws IOException {
  hpackWriter.writeInt(30,31,0);
  assertBytes(30);
  assertEquals(31,newReader(byteStream(0)).readInt(31,31));
}
