@Test public void testAnyAIDecoder_3() throws Exception {
  CharSequence data=header + numeric_10 + numeric2alpha+ alpha2isoiec646+ i646_B+ i646_C+ isoiec646_2alpha+ alpha_A+ alpha2numeric+ numeric_10;
  String expected="(10)BCA10";
  assertCorrectBinaryString(data,expected);
}
