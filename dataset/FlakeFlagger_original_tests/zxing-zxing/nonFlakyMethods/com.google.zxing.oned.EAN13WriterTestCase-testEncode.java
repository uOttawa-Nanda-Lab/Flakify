@Test public void testEncode() throws WriterException {
  CharSequence testStr="00010100010110100111011001100100110111101001110101010110011011011001000010101110010011101000100101000";
  BitMatrix result=new EAN13Writer().encode("5901234123457",BarcodeFormat.EAN_13,testStr.length(),0);
  for (int i=0; i < testStr.length(); i++) {
    assertEquals("Element " + i,testStr.charAt(i) == '1',result.get(i,0));
  }
}
