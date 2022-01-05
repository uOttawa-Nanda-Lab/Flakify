@Test(expected=IndexOutOfBoundsException.class) public void testSmallLength(){
  Bytes.bytes2base64("dubbo".getBytes(),0,-1);
}
