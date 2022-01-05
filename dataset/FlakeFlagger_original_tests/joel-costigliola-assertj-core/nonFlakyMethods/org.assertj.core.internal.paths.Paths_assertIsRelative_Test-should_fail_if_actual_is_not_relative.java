@Test public void should_fail_if_actual_is_not_relative(){
  when(actual.isAbsolute()).thenReturn(true);
  try {
    paths.assertIsRelative(info,actual);
    wasExpectingAssertionError();
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeRelativePath(actual));
  }
}
