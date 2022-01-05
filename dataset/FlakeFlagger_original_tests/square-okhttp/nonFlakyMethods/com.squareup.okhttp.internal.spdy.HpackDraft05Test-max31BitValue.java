@Test public void max31BitValue() throws IOException {
  hpackWriter.writeInt(0x7fffffff,31,0);
  assertBytes(31,224,255,255,255,7);
  assertEquals(0x7fffffff,newReader(byteStream(224,255,255,255,7)).readInt(31,31));
}
