@Test public void should_fail_if_actual_is_in_Iterable(){
  AssertionInfo info=someInfo();
  try {
    objects.assertIsNotIn(info,"Yoda",values);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotBeIn("Yoda",values));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
