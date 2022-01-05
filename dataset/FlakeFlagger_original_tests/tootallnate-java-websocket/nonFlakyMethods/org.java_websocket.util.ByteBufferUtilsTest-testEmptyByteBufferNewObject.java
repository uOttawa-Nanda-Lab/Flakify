@Test public void testEmptyByteBufferNewObject(){
  ByteBuffer byteBuffer0=ByteBufferUtils.getEmptyByteBuffer();
  ByteBuffer byteBuffer1=ByteBufferUtils.getEmptyByteBuffer();
  assertTrue("Allocated new object",byteBuffer0 != byteBuffer1);
}
