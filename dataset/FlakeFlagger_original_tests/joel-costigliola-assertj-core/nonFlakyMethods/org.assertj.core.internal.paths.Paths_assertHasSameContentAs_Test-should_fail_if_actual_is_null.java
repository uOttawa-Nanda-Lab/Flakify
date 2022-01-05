@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  when(nioFilesWrapper.isReadable(other)).thenReturn(true);
  paths.assertHasSameContentAs(someInfo(),null,other);
}
