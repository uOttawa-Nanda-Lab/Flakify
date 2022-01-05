@Test public void testAnyAIDecoder_646FNC1() throws Exception {
  CharSequence data=header + numeric_10 + numeric2alpha+ alpha_A+ isoiec646_2alpha+ i646_B+ i646_FNC1;
  String expected="(10)AB";
  assertCorrectBinaryString(data,expected);
}
