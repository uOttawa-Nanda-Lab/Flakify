@Test public void testDataMatrixTooSmall(){
  int tooSmall=8;
  DataMatrixWriter writer=new DataMatrixWriter();
  BitMatrix matrix=writer.encode("http://www.google.com/",BarcodeFormat.DATA_MATRIX,tooSmall,tooSmall,null);
  assertNotNull(matrix);
  assertTrue(tooSmall < matrix.getWidth());
  assertTrue(tooSmall < matrix.getHeight());
}
