@Test(expected=IndexOutOfBoundsException.class) public void testBytes2HexWithWrongOffset(){
  Bytes.bytes2hex("hello".getBytes(),-1,1);
}
