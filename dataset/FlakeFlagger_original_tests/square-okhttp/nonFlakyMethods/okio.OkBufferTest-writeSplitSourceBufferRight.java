/** 
 * The big part of source's first segment is staying put. 
 */
@Test public void writeSplitSourceBufferRight() throws Exception {
  int writeSize=Segment.SIZE / 2 - 1;
  OkBuffer sink=new OkBuffer();
  sink.writeUtf8(repeat('b',Segment.SIZE - 10));
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('a',Segment.SIZE * 2));
  sink.write(source,writeSize);
  assertEquals(asList(Segment.SIZE - 10,writeSize),sink.segmentSizes());
  assertEquals(asList(Segment.SIZE - writeSize,Segment.SIZE),source.segmentSizes());
}
