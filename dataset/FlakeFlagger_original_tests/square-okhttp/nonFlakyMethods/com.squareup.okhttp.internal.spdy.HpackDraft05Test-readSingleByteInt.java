@Test public void readSingleByteInt() throws IOException {
  assertEquals(10,newReader(byteStream()).readInt(10,31));
  assertEquals(10,newReader(byteStream()).readInt(0xe0 | 10,31));
}
