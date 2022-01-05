@Test public void should_fail_if_actual_has_message_not_containing_with_expected_description(){
  AssertionInfo info=someInfo();
  try {
    throwables.assertHasMessageContaining(info,actual,"expected descirption part");
    fail("AssertionError expected");
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldContain(actual.getMessage(),"expected descirption part"));
  }
}
