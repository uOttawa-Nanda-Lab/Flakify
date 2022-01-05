@Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size(){
  AssertionInfo info=someInfo();
  try {
    arrays.assertHasSize(info,actual,6);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldHaveSize(actual,actual.length,6));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
