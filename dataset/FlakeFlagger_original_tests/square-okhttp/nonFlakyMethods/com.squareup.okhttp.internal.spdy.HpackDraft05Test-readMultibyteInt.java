@Test public void readMultibyteInt() throws IOException {
  assertEquals(1337,newReader(byteStream(154,10)).readInt(31,31));
}
