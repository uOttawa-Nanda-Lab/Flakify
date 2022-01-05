@Test(expected=IndexOutOfBoundsException.class) public void testWrongLength(){
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes());
  stream.read(new byte[1],0,100);
}
