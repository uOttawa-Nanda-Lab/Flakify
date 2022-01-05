@Test public void testTransferByteBufferCheckNullDest(){
  ByteBuffer source=ByteBuffer.wrap(smallArray);
  try {
    ByteBufferUtils.transferByteBuffer(source,null);
    fail("IllegalArgumentException should be thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
