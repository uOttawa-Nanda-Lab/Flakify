@Test public void testEncode() throws WriterException {
  CharSequence testStr="00010101000110110111011000100010110101111011110101010111001011101001001110110011011011001011100101000";
  BitMatrix result=new UPCAWriter().encode("485963095124",BarcodeFormat.UPC_A,testStr.length(),0);
  for (int i=0; i < testStr.length(); i++) {
    assertEquals("Element " + i,testStr.charAt(i) == '1',result.get(i,0));
  }
}
