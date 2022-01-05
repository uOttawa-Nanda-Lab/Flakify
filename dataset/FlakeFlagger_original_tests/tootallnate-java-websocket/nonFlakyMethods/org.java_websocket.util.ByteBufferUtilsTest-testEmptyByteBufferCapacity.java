@Test public void testEmptyByteBufferCapacity(){
  ByteBuffer byteBuffer=ByteBufferUtils.getEmptyByteBuffer();
  assertEquals("capacity must be 0",0,byteBuffer.capacity());
}
