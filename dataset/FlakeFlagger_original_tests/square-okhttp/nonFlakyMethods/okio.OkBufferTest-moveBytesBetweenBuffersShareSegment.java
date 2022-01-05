@Test public void moveBytesBetweenBuffersShareSegment() throws Exception {
  int size=(Segment.SIZE / 2) - 1;
  List<Integer> segmentSizes=moveBytesBetweenBuffers(repeat('a',size),repeat('b',size));
  assertEquals(asList(size * 2),segmentSizes);
}
