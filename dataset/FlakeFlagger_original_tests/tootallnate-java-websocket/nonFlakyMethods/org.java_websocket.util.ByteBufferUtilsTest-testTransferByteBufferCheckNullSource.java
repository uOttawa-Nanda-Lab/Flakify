@Test public void testTransferByteBufferCheckNullSource(){
  ByteBuffer dest=ByteBuffer.wrap(smallArray);
  try {
    ByteBufferUtils.transferByteBuffer(null,dest);
    fail("IllegalArgumentException should be thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
