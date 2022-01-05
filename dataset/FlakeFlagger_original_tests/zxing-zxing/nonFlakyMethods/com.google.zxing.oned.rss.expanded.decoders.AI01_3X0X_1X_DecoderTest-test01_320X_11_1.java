@Test public void test01_320X_11_1() throws Exception {
  CharSequence data=header_320x_11 + compressedGtin_900123456798908 + compressed20bitWeight_1750+ compressedDate_March_12th_2010;
  String expected="(01)90012345678908(3200)001750(11)100312";
  assertCorrectBinaryString(data,expected);
}
