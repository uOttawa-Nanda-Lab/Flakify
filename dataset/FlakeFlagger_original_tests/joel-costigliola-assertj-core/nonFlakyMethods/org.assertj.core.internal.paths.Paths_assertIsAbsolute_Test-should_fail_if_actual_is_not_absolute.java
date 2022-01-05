@Test public void should_fail_if_actual_is_not_absolute(){
  when(actual.isAbsolute()).thenReturn(false);
  try {
    paths.assertIsAbsolute(info,actual);
    wasExpectingAssertionError();
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeAbsolutePath(actual));
  }
}
