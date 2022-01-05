@Test(expected=IndexOutOfBoundsException.class) public void getByteArrayBoundaryCheck1(){
  buffer.getBytes(-1,new byte[0]);
}
