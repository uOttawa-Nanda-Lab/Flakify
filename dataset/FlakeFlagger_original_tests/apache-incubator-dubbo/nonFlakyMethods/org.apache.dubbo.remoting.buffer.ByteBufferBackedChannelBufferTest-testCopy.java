@Test public void testCopy(){
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    buffer.setByte(i,value);
  }
  final int readerIndex=CAPACITY / 3;
  final int writerIndex=CAPACITY * 2 / 3;
  buffer.setIndex(readerIndex,writerIndex);
  ChannelBuffer copy=buffer.copy();
  assertEquals(0,copy.readerIndex());
  assertEquals(buffer.readableBytes(),copy.writerIndex());
  assertEquals(buffer.readableBytes(),copy.capacity());
  for (int i=0; i < copy.capacity(); i++) {
    assertEquals(buffer.getByte(i + readerIndex),copy.getByte(i));
  }
  buffer.setByte(readerIndex,(byte)(buffer.getByte(readerIndex) + 1));
  assertTrue(buffer.getByte(readerIndex) != copy.getByte(0));
  copy.setByte(1,(byte)(copy.getByte(1) + 1));
  assertTrue(buffer.getByte(readerIndex + 1) != copy.getByte(1));
}
