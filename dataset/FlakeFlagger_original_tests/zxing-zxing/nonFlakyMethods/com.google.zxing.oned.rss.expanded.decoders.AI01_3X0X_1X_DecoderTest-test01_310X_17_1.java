@Test public void test01_310X_17_1() throws Exception {
  CharSequence data=header_310x_17 + compressedGtin_900123456798908 + compressed20bitWeight_1750+ compressedDate_March_12th_2010;
  String expected="(01)90012345678908(3100)001750(17)100312";
  assertCorrectBinaryString(data,expected);
}
