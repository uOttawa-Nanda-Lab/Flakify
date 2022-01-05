@Test public void testBugInBitVectorNumBytes() throws WriterException {
  StringBuilder builder=new StringBuilder(3518);
  for (int x=0; x < 3518; x++) {
    builder.append('0');
  }
  Encoder.encode(builder.toString(),ErrorCorrectionLevel.L);
}
