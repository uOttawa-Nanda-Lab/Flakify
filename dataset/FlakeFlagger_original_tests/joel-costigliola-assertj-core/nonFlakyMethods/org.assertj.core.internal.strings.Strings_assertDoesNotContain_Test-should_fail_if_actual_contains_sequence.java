@Test public void should_fail_if_actual_contains_sequence(){
  AssertionInfo info=someInfo();
  try {
    strings.assertDoesNotContain(info,"Yoda","oda");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotContain("Yoda","oda"));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
