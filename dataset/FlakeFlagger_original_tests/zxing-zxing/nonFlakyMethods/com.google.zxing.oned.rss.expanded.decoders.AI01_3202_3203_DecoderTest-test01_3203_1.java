@Test public void test01_3203_1() throws Exception {
  CharSequence data=header + compressedGtin_900123456798908 + compressed15bitWeight_11750;
  String expected="(01)90012345678908(3203)001750";
  assertCorrectBinaryString(data,expected);
}
