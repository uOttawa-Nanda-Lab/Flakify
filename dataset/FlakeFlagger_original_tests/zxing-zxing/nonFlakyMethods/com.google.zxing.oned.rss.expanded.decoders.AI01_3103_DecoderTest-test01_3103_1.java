@Test public void test01_3103_1() throws Exception {
  CharSequence data=header + compressedGtin_900123456798908 + compressed15bitWeight_1750;
  String expected="(01)90012345678908(3103)001750";
  assertCorrectBinaryString(data,expected);
}
