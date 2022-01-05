@Test public void cloneMultipleSegments() throws Exception {
  OkBuffer original=new OkBuffer();
  original.writeUtf8(repeat('a',Segment.SIZE * 3));
  OkBuffer clone=original.clone();
  original.writeUtf8(repeat('b',Segment.SIZE * 3));
  clone.writeUtf8(repeat('c',Segment.SIZE * 3));
  assertEquals(repeat('a',Segment.SIZE * 3) + repeat('b',Segment.SIZE * 3),original.readUtf8(Segment.SIZE * 6));
  assertEquals(repeat('a',Segment.SIZE * 3) + repeat('c',Segment.SIZE * 3),clone.readUtf8(Segment.SIZE * 6));
}
