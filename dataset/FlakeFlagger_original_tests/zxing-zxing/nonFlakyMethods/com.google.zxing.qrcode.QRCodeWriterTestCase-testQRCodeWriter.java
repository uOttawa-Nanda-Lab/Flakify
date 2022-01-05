@Test public void testQRCodeWriter() throws WriterException {
  int bigEnough=256;
  QRCodeWriter writer=new QRCodeWriter();
  BitMatrix matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,bigEnough,bigEnough,null);
  assertNotNull(matrix);
  assertEquals(bigEnough,matrix.getWidth());
  assertEquals(bigEnough,matrix.getHeight());
  int tooSmall=20;
  matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,tooSmall,tooSmall,null);
  assertNotNull(matrix);
  assertTrue(tooSmall < matrix.getWidth());
  assertTrue(tooSmall < matrix.getHeight());
  int strangeWidth=500;
  int strangeHeight=100;
  matrix=writer.encode("http://www.google.com/",BarcodeFormat.QR_CODE,strangeWidth,strangeHeight,null);
  assertNotNull(matrix);
  assertEquals(strangeWidth,matrix.getWidth());
  assertEquals(strangeHeight,matrix.getHeight());
}
