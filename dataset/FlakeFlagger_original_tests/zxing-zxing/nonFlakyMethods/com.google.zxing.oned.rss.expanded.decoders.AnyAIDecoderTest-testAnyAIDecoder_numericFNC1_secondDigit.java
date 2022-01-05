@Test public void testAnyAIDecoder_numericFNC1_secondDigit() throws Exception {
  CharSequence data=header + numeric_10 + numeric_1FNC1;
  String expected="(10)1";
  assertCorrectBinaryString(data,expected);
}
