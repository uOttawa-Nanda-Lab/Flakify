@Test public void testEncode() throws WriterException {
  CharSequence resultStr="0000000000" + "1001001011011010100101010110010110101001010100110101100101010110110101101001001011" + "0000000000";
  BitMatrix result=new CodaBarWriter().encode("B515-3/B",BarcodeFormat.CODABAR,resultStr.length(),0);
  for (int i=0; i < resultStr.length(); i++) {
    assertEquals("Element " + i,resultStr.charAt(i) == '1',result.get(i,0));
  }
}
