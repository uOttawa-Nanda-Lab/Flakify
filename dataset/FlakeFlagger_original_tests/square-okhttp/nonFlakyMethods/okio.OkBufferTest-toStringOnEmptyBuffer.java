@Test public void toStringOnEmptyBuffer() throws Exception {
  OkBuffer buffer=new OkBuffer();
  assertEquals("OkBuffer[size=0]",buffer.toString());
}
