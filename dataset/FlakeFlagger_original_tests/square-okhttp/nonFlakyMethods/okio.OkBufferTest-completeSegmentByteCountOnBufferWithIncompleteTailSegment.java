@Test public void completeSegmentByteCountOnBufferWithIncompleteTailSegment() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',Segment.SIZE * 4 - 10));
  assertEquals(Segment.SIZE * 3,buffer.completeSegmentByteCount());
}
