@Test(expected=IllegalArgumentException.class) public void testWrongBase64Code(){
  Bytes.bytes2base64("dubbo".getBytes(),0,1,new char[]{'a'});
}
