@Test(expected=IndexOutOfBoundsException.class) public void testWrongOffSet(){
  Bytes.bytes2base64("dubbo".getBytes(),-1,1);
}
