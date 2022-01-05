@Test public void should_return_diffs_if_content_of_actual_is_longer_than_content_of_expected() throws IOException {
  actual=stream("base","line_0","line_1");
  expected=stream("base","line_0");
  List<String> diffs=diff.diff(actual,expected);
  assertEquals(1,diffs.size());
  assertEquals("line:<3>, expected:<EOF> but was:<line_1>",diffs.get(0));
}
