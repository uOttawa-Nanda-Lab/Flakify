@Test(expected=IndexOutOfBoundsException.class) public void setIndexBoundaryCheck2(){
  buffer.setIndex(CAPACITY / 2,CAPACITY / 4);
}
