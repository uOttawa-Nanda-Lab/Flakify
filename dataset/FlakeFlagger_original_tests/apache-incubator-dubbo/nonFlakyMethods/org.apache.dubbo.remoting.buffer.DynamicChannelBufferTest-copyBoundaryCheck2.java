@Test(expected=IndexOutOfBoundsException.class) public void copyBoundaryCheck2(){
  buffer.copy(0,buffer.capacity() + 1);
}
