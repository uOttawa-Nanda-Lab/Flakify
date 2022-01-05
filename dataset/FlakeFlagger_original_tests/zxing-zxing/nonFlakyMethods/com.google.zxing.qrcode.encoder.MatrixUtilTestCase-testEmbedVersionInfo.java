@Test public void testEmbedVersionInfo() throws WriterException {
  ByteMatrix matrix=new ByteMatrix(21,21);
  MatrixUtil.clearMatrix(matrix);
  MatrixUtil.maybeEmbedVersionInfo(Version.getVersionForNumber(7),matrix);
  String expected="                     0 0 1                \n" + "                     0 1 0                \n" + "                     0 1 0                \n"+ "                     0 1 1                \n"+ "                     1 1 1                \n"+ "                     0 0 0                \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ " 0 0 0 0 1 0                              \n"+ " 0 1 1 1 1 0                              \n"+ " 1 0 0 1 1 0                              \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n"+ "                                          \n";
  assertEquals(expected,matrix.toString());
}
