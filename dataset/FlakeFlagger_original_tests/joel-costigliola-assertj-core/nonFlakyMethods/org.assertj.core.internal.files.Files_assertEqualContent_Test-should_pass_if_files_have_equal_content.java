@Test public void should_pass_if_files_have_equal_content() throws IOException {
  when(diff.diff(actual,expected)).thenReturn(new ArrayList<String>());
  files.assertSameContentAs(someInfo(),actual,expected);
}
