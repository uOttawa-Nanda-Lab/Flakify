@Test public void testTransferByteBufferSmallToBig(){
  ByteBuffer small=ByteBuffer.wrap(smallArray);
  ByteBuffer big=ByteBuffer.wrap(bigArray);
  ByteBufferUtils.transferByteBuffer(small,big);
  assertArrayEquals("Small bytebuffer should not change",smallArray,small.array());
  assertEquals("Big bytebuffer not same to source 0",smallArray[0],big.get(0));
  assertEquals("Big bytebuffer not same to source 1",smallArray[1],big.get(1));
  assertEquals("Big bytebuffer not same to source 2",smallArray[2],big.get(2));
  assertEquals("Big bytebuffer not same to source 3",smallArray[3],big.get(3));
  assertEquals("Big bytebuffer not same to source 4",smallArray[4],big.get(4));
  assertEquals("Big bytebuffer not same to source 5",bigArray[5],big.get(5));
  assertEquals("Big bytebuffer not same to source 6",bigArray[6],big.get(6));
  assertEquals("Big bytebuffer not same to source 7",bigArray[7],big.get(7));
  assertEquals("Big bytebuffer not same to source 8",bigArray[8],big.get(8));
}
