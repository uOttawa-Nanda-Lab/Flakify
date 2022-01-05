@Test(expected=IndexOutOfBoundsException.class) public void getByteBoundaryCheck1(){
  buffer.getByte(-1);
}
