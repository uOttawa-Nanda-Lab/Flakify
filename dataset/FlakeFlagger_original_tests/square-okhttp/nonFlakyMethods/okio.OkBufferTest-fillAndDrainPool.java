@Test public void fillAndDrainPool() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.write(new byte[(int)SegmentPool.MAX_SIZE]);
  buffer.write(new byte[(int)SegmentPool.MAX_SIZE]);
  assertEquals(0,SegmentPool.INSTANCE.byteCount);
  buffer.readByteString(SegmentPool.MAX_SIZE);
  assertEquals(SegmentPool.MAX_SIZE,SegmentPool.INSTANCE.byteCount);
  buffer.readByteString(SegmentPool.MAX_SIZE);
  assertEquals(SegmentPool.MAX_SIZE,SegmentPool.INSTANCE.byteCount);
  buffer.write(new byte[(int)SegmentPool.MAX_SIZE]);
  assertEquals(0,SegmentPool.INSTANCE.byteCount);
  buffer.write(new byte[(int)SegmentPool.MAX_SIZE]);
  assertEquals(0,SegmentPool.INSTANCE.byteCount);
}
