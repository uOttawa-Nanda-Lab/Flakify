@Test(expected=IndexOutOfBoundsException.class) public void testBytes2HexWithWrongLength(){
  Bytes.bytes2hex("hello".getBytes(),0,6);
}
