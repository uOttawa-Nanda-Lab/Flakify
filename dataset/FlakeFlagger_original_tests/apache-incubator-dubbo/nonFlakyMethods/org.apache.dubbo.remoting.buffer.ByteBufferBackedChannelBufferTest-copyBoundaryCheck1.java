@Test(expected=IndexOutOfBoundsException.class) public void copyBoundaryCheck1(){
  buffer.copy(-1,0);
}
