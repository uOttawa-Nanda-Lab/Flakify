@Test(expected=IndexOutOfBoundsException.class) public void getByteBoundaryCheck2(){
  buffer.getByte(buffer.capacity());
}
