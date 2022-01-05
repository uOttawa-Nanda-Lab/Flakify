@Test public void should_return_diff_if_inputstreams_differ_on_one_byte() throws IOException {
  actual=stream(0xCA,0xFE,0xBA,0xBE);
  expected=stream(0xCA,0xFE,0xBE,0xBE);
  BinaryDiffResult result=binaryDiff.diff(actual,expected);
  assertEquals(2,result.offset);
  assertEquals("0xBA",result.actual);
  assertEquals("0xBE",result.expected);
}
