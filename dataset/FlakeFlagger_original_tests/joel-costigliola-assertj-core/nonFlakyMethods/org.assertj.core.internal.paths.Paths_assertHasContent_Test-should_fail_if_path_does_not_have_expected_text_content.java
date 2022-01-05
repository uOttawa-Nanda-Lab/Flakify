@Test public void should_fail_if_path_does_not_have_expected_text_content() throws IOException {
  List<String> diffs=newArrayList("line:1, expected:<line1> but was:<EOF>");
  when(diff.diff(path.toFile(),expected,charset)).thenReturn(diffs);
  when(nioFilesWrapper.exists(path)).thenReturn(true);
  when(nioFilesWrapper.isReadable(path)).thenReturn(true);
  AssertionInfo info=someInfo();
  try {
    paths.assertHasContent(info,path,expected,charset);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveContent(path.toFile(),charset,diffs));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
