@Test public void should_return_diff_if_actual_is_shorter() throws IOException {
  actual=stream(0xCA,0xFE,0xBA);
  expected=stream(0xCA,0xFE,0xBA,0xBE);
  BinaryDiffResult result=binaryDiff.diff(actual,expected);
  assertEquals(3,result.offset);
  assertEquals("EOF",result.actual);
  assertEquals("0xBE",result.expected);
}
