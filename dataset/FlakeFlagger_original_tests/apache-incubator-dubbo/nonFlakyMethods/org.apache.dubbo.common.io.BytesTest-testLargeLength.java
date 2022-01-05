@Test(expected=IndexOutOfBoundsException.class) public void testLargeLength(){
  Bytes.bytes2base64("dubbo".getBytes(),0,100000);
}
