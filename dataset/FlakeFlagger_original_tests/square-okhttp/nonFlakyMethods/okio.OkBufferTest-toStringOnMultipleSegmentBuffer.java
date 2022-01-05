@Test public void toStringOnMultipleSegmentBuffer() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',6144));
  assertEquals("OkBuffer[size=6144 md5=d890021f28522533c1cc1b9b1f83ce73]",buffer.toString());
}
