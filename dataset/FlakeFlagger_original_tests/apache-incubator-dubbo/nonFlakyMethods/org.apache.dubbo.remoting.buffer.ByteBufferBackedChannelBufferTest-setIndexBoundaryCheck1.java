@Test(expected=IndexOutOfBoundsException.class) public void setIndexBoundaryCheck1(){
  buffer.setIndex(-1,CAPACITY);
}
