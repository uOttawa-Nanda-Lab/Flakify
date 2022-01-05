@Test(expected=IndexOutOfBoundsException.class) public void copyBoundaryCheck4(){
  buffer.copy(buffer.capacity(),1);
}
