@Test public void completeSegmentByteCountOnEmptyBuffer() throws Exception {
  OkBuffer buffer=new OkBuffer();
  assertEquals(0,buffer.completeSegmentByteCount());
}
