@Test(expected=IndexOutOfBoundsException.class) public void writerIndexBoundaryCheck1(){
  buffer.writerIndex(-1);
}
