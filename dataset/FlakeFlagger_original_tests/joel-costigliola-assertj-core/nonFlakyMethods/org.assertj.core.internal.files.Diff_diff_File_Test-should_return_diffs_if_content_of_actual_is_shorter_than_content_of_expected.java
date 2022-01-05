@Test public void should_return_diffs_if_content_of_actual_is_shorter_than_content_of_expected() throws IOException {
  writer.write(actual,"line_0");
  writer.write(expected,"line_0","line_1");
  List<String> diffs=diff.diff(actual,expected);
  assertEquals(1,diffs.size());
  assertEquals("line:<2>, expected:<line_1> but was:<EOF>",diffs.get(0));
}
