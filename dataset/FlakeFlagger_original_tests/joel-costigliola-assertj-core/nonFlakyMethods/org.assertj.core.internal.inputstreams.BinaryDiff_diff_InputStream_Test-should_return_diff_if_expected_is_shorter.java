@Test public void should_return_diff_if_expected_is_shorter() throws IOException {
  actual=stream(0xCA,0xFE,0xBA,0xBE);
  expected=stream(0xCA,0xFE,0xBA);
  BinaryDiffResult result=binaryDiff.diff(actual,expected);
  assertEquals(3,result.offset);
  assertEquals("0xBE",result.actual);
  assertEquals("EOF",result.expected);
}
