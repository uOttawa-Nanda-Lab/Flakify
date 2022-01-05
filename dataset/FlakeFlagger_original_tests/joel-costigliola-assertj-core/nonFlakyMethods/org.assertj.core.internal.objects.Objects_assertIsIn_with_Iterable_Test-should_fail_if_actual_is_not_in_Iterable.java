@Test public void should_fail_if_actual_is_not_in_Iterable(){
  AssertionInfo info=someInfo();
  try {
    objects.assertIsIn(info,"Luke",values);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeIn("Luke",values));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
