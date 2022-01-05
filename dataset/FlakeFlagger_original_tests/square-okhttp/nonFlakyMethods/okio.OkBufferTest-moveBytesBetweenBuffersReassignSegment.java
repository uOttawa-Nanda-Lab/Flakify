@Test public void moveBytesBetweenBuffersReassignSegment() throws Exception {
  int size=(Segment.SIZE / 2) + 1;
  List<Integer> segmentSizes=moveBytesBetweenBuffers(repeat('a',size),repeat('b',size));
  assertEquals(asList(size,size),segmentSizes);
}
