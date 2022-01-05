@Test public void should_fail_if_actual_does_not_contain_given_string(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsOnlyOnce(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyOnce("Yoda","Luke",0));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
