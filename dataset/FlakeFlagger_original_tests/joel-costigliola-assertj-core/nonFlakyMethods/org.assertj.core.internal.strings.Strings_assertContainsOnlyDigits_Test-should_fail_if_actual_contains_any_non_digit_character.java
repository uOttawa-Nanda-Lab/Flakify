@Test public void should_fail_if_actual_contains_any_non_digit_character(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsOnlyDigits(info,"10$");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyDigits("10$",'$',2));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
