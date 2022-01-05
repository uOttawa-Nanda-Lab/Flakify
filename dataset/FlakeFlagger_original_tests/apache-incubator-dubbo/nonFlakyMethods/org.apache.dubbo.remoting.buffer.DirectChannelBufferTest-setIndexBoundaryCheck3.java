@Test(expected=IndexOutOfBoundsException.class) public void setIndexBoundaryCheck3(){
  buffer.setIndex(0,CAPACITY + 1);
}
