@Test public void completeSegmentByteCountOnBufferWithFullSegments() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',Segment.SIZE * 4));
  assertEquals(Segment.SIZE * 4,buffer.completeSegmentByteCount());
}
