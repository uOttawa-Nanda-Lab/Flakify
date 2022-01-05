@Test public void should_fail_if_actual_contains_given_string_more_than_once(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsOnlyOnce(info,"Yodayoda","oda");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyOnce("Yodayoda","oda",2));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
