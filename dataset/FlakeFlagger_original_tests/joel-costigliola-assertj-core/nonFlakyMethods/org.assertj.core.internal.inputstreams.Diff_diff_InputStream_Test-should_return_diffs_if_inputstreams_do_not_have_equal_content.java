@Test public void should_return_diffs_if_inputstreams_do_not_have_equal_content() throws IOException {
  actual=stream("base","line_0","line_1");
  expected=stream("base","line0","line1");
  List<String> diffs=diff.diff(actual,expected);
  assertEquals(2,diffs.size());
  assertEquals("line:<2>, expected:<line0> but was:<line_0>",diffs.get(0));
  assertEquals("line:<3>, expected:<line1> but was:<line_1>",diffs.get(1));
}
