@Test(expected=IndexOutOfBoundsException.class) public void getByteBufferBoundaryCheck(){
  buffer.getBytes(-1,ByteBuffer.allocate(0));
}
