@Test public void should_fail_if_actual_path_does_not_exist(){
  AssertionInfo info=someInfo();
  when(nioFilesWrapper.exists(actual)).thenReturn(false);
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  try {
    paths.assertHasSameContentAs(info,actual,other);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldExist(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
