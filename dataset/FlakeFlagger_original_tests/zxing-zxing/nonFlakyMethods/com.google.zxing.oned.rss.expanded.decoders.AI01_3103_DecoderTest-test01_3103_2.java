@Test public void test01_3103_2() throws Exception {
  CharSequence data=header + compressedGtin_900000000000008 + compressed15bitWeight_0;
  String expected="(01)90000000000003(3103)000000";
  assertCorrectBinaryString(data,expected);
}
