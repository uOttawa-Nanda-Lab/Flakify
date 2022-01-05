@Test public void testTransferByteBufferBigToSmall(){
  ByteBuffer small=ByteBuffer.wrap(smallArray);
  ByteBuffer big=ByteBuffer.wrap(bigArray);
  ByteBufferUtils.transferByteBuffer(big,small);
  assertArrayEquals("Big bytebuffer should not change",bigArray,big.array());
  assertEquals("Small bytebuffer not same to source 0",bigArray[0],small.get(0));
  assertEquals("Small bytebuffer not same to source 1",bigArray[1],small.get(1));
  assertEquals("Small bytebuffer not same to source 2",bigArray[2],small.get(2));
  assertEquals("Small bytebuffer not same to source 3",bigArray[3],small.get(3));
  assertEquals("Small bytebuffer not same to source 4",bigArray[4],small.get(4));
}
