@Test public void should_return_diffs_if_content_of_actual_is_shorter_than_content_of_expected() throws IOException {
  actual=stream("base","line_0");
  expected=stream("base","line_0","line_1");
  List<String> diffs=diff.diff(actual,expected);
  assertEquals(1,diffs.size());
  assertEquals("line:<3>, expected:<line_1> but was:<EOF>",diffs.get(0));
}
