@Test public void should_fail_if_files_do_not_have_equal_content() throws IOException {
  List<String> diffs=newArrayList("line:1, expected:<line1> but was:<EOF>");
  when(diff.diff(actual,expected)).thenReturn(diffs);
  AssertionInfo info=someInfo();
  try {
    files.assertSameContentAs(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,ShouldHaveSameContent.shouldHaveSameContent(actual,expected,diffs));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
