@Test public void zero() throws IOException {
  hpackWriter.writeInt(0,31,0);
  assertBytes(0);
  assertEquals(0,newReader(byteStream()).readInt(0,31));
}
