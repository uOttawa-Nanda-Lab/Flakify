@Test public void should_fail_if_actual_has_parent() throws Exception {
  AssertionInfo info=someInfo();
  when(actual.getParentFile()).thenReturn(mock(File.class));
  try {
    files.assertHasNoParent(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveNoParent(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
