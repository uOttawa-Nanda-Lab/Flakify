@Test public void should_fail_if_actual_toString_is_not_the_expected_String(){
  AssertionInfo info=someInfo();
  try {
    objects.assertHasToString(info,actual,"bar");
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveToString(actual,"bar"));
  }
}
