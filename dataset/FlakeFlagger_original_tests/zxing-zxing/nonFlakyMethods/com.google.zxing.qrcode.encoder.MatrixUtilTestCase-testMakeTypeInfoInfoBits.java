@Test public void testMakeTypeInfoInfoBits() throws WriterException {
  BitArray bits=new BitArray();
  MatrixUtil.makeTypeInfoBits(ErrorCorrectionLevel.M,5,bits);
  assertEquals(" X......X X..XXX.",bits.toString());
}
