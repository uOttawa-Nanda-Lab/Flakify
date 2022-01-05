@Test public void should_throw_error_if_actual_is_null() throws Exception {
  thrown.expectAssertionError(actualIsNull());
  files.assertHasNoParent(someInfo(),null);
}
