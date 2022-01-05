@Test(expected=IndexOutOfBoundsException.class) public void getByteArrayBoundaryCheck2(){
  buffer.getBytes(-1,new byte[0],0,0);
}
