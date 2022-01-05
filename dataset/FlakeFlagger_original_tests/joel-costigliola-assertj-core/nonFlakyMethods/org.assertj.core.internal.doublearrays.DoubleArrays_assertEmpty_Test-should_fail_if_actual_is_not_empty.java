@Test public void should_fail_if_actual_is_not_empty(){
  AssertionInfo info=someInfo();
  double[] actual={6d,8d};
  try {
    arrays.assertEmpty(info,actual);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeEmpty(actual));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
