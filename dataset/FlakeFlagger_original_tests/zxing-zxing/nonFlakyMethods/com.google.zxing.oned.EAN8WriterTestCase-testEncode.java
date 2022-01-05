@Test public void testEncode() throws WriterException {
  CharSequence testStr="0001010001011010111101111010110111010101001110111001010001001011100101000";
  BitMatrix result=new EAN8Writer().encode("96385074",BarcodeFormat.EAN_8,testStr.length(),0);
  for (int i=0; i < testStr.length(); i++) {
    assertEquals("Element " + i,testStr.charAt(i) == '1',result.get(i,0));
  }
}
