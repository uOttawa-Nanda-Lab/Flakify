@Test public void should_pass_if_inputstreams_have_equal_content() throws IOException {
  when(diff.diff(actual,expected)).thenReturn(new ArrayList<String>());
  inputStreams.assertSameContentAs(someInfo(),actual,expected);
}
