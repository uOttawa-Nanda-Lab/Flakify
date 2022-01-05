@Test public void testAnyAIDecoder_2() throws Exception {
  CharSequence data=header + numeric_10 + numeric_12+ numeric2alpha+ alpha_A+ alpha2isoiec646+ i646_B;
  String expected="(10)12AB";
  assertCorrectBinaryString(data,expected);
}
