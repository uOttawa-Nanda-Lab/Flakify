@Test public void should_fail_if_other_is_not_a_readable_file(){
  AssertionInfo info=someInfo();
  when(nioFilesWrapper.isReadable(other)).thenReturn(false);
  try {
    paths.assertHasSameContentAs(info,actual,other);
    failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
  }
 catch (  IllegalArgumentException e) {
    assertThat(e).hasMessage(format("The given Path <%s> to compare actual content to should be readable",other));
  }
}
