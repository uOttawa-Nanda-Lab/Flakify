@Test public void should_fail_if_actual_is_not_a_readable_file(){
  AssertionInfo info=someInfo();
  when(nioFilesWrapper.exists(actual)).thenReturn(true);
  when(nioFilesWrapper.isReadable(actual)).thenReturn(false);
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  try {
    paths.assertHasSameContentAs(info,actual,other);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeReadable(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
