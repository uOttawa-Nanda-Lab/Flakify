@Test public void should_return_diff_if_inputstreams_differ_on_one_byte() throws IOException {
  writer.write(actual,"test");
  expected=("fest" + LINE_SEPARATOR).getBytes();
  BinaryDiffResult result=binaryDiff.diff(actual,expected);
  assertEquals(0,result.offset);
  assertEquals("0x74",result.actual);
  assertEquals("0x66",result.expected);
}
