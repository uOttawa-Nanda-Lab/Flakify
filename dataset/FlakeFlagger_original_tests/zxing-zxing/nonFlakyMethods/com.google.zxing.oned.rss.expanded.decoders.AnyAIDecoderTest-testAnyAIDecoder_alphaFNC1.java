@Test public void testAnyAIDecoder_alphaFNC1() throws Exception {
  CharSequence data=header + numeric_10 + numeric2alpha+ alpha_A+ alpha_FNC1;
  String expected="(10)A";
  assertCorrectBinaryString(data,expected);
}
