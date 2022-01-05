@Test public void should_fail_if_actual_has_not_expected_message(){
  AssertionInfo info=someInfo();
  try {
    throwables.assertHasMessage(info,actual,"expected message");
    fail("AssertionError expected");
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveMessage(actual,"expected message"));
  }
}
