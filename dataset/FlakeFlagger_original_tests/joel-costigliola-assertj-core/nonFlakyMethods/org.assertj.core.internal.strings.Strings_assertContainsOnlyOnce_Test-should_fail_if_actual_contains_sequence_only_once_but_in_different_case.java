@Test public void should_fail_if_actual_contains_sequence_only_once_but_in_different_case(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsOnlyOnce(info,"Yoda","yo");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyOnce("Yoda","yo",0));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
