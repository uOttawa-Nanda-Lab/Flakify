@Test public void testAddChecksumAndEncode() throws WriterException {
  CharSequence testStr="00010100110010010011011110101000110110001010111101010100010010010001110100111001011001101101100101000";
  BitMatrix result=new UPCAWriter().encode("12345678901",BarcodeFormat.UPC_A,testStr.length(),0);
  for (int i=0; i < testStr.length(); i++) {
    assertEquals("Element " + i,testStr.charAt(i) == '1',result.get(i,0));
  }
}
