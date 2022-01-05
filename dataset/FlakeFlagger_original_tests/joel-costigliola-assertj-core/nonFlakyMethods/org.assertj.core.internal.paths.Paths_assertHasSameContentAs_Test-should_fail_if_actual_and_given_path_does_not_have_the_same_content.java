@Test public void should_fail_if_actual_and_given_path_does_not_have_the_same_content() throws IOException {
  List<String> diffs=newArrayList("line:1, other:<line1> but was:<EOF>");
  when(diff.diff(actual.toFile(),other.toFile())).thenReturn(diffs);
  when(nioFilesWrapper.exists(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  AssertionInfo info=someInfo();
  try {
    paths.assertHasSameContentAs(info,actual,other);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveSameContent(actual.toFile(),other.toFile(),diffs));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
