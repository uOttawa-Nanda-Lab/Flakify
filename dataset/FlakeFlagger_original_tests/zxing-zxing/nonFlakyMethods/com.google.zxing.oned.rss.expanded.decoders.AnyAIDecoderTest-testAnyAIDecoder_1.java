@Test public void testAnyAIDecoder_1() throws Exception {
  CharSequence data=header + numeric_10 + numeric_12+ numeric2alpha+ alpha_A+ alpha2numeric+ numeric_12;
  String expected="(10)12A12";
  assertCorrectBinaryString(data,expected);
}
