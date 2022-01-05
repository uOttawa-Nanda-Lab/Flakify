@Test(expected=IndexOutOfBoundsException.class) public void testWrongOffset(){
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes());
  stream.read(new byte[1],-1,1);
}
