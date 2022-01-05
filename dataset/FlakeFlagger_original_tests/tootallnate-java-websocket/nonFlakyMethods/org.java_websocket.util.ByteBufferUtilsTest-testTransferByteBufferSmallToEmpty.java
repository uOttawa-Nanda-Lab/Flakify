@Test public void testTransferByteBufferSmallToEmpty(){
  ByteBuffer small=ByteBuffer.wrap(smallArray);
  ByteBuffer empty=ByteBufferUtils.getEmptyByteBuffer();
  ByteBufferUtils.transferByteBuffer(small,empty);
  assertArrayEquals("Small bytebuffer should not change",smallArray,small.array());
  assertEquals("Capacity of the empty bytebuffer should still be 0",0,empty.capacity());
}
