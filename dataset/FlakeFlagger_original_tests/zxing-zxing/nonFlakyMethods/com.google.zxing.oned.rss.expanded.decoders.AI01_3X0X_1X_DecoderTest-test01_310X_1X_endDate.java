@Test public void test01_310X_1X_endDate() throws Exception {
  CharSequence data=header_310x_11 + compressedGtin_900123456798908 + compressed20bitWeight_1750+ compressedDate_End;
  String expected="(01)90012345678908(3100)001750";
  assertCorrectBinaryString(data,expected);
}
