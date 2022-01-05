@Test(expected=NotFoundException.class) public void test01_3103_invalid() throws Exception {
  CharSequence data=header + compressedGtin_900123456798908 + compressed15bitWeight_1750+ "..";
  assertCorrectBinaryString(data,"");
}
