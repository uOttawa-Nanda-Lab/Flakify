@Test public void should_fail_if_inputstreams_do_not_have_equal_content() throws IOException {
  List<String> diffs=newArrayList("line:1, expected:<line1> but was:<EOF>");
  when(diff.diff(actual,expected)).thenReturn(diffs);
  AssertionInfo info=someInfo();
  try {
    inputStreams.assertSameContentAs(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveSameContent(actual,expected,diffs));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
