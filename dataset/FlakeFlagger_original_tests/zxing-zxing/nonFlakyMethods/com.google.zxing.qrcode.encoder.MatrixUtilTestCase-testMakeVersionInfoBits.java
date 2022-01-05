@Test public void testMakeVersionInfoBits() throws WriterException {
  BitArray bits=new BitArray();
  MatrixUtil.makeVersionInfoBits(Version.getVersionForNumber(7),bits);
  assertEquals(" ...XXXXX ..X..X.X ..",bits.toString());
}
