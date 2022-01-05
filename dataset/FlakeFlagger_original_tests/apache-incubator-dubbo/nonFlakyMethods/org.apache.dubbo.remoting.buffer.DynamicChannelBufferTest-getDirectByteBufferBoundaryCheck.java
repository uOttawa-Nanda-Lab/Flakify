@Test(expected=IndexOutOfBoundsException.class) public void getDirectByteBufferBoundaryCheck(){
  buffer.getBytes(-1,ByteBuffer.allocateDirect(0));
}
