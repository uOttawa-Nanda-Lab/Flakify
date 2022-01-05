@Test public void testTransferByteBufferCheckNullBoth(){
  try {
    ByteBufferUtils.transferByteBuffer(null,null);
    fail("IllegalArgumentException should be thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
