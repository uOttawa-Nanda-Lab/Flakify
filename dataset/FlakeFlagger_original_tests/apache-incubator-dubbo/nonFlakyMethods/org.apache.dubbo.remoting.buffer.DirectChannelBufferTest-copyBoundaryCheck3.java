@Test(expected=IndexOutOfBoundsException.class) public void copyBoundaryCheck3(){
  buffer.copy(buffer.capacity() + 1,0);
}
