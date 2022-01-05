@Test public void should_fail_if_actual_does_not_contain_all_given_strings(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsSequence(info,"Yoda",array("Yo","da","Han"));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain("Yoda",array("Yo","da","Han"),newLinkedHashSet("Han")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
