@Test public void should_fail_if_actual_contains_sequence_but_in_different_case(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContains(info,"Yoda","yo");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContain("Yoda","yo"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
