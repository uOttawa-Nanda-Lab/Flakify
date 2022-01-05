@Test public void moveBytesBetweenBuffersMultipleSegments() throws Exception {
  int size=3 * Segment.SIZE + 1;
  List<Integer> segmentSizes=moveBytesBetweenBuffers(repeat('a',size),repeat('b',size));
  assertEquals(asList(Segment.SIZE,Segment.SIZE,Segment.SIZE,1,Segment.SIZE,Segment.SIZE,Segment.SIZE,1),segmentSizes);
}
