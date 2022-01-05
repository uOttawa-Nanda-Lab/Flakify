@Test(expected=NullPointerException.class) public void testReadEmptyByteArray(){
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes());
  stream.read(null,0,1);
}
