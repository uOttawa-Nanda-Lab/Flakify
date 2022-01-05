@Test public void should_fail_if_actual_contains_duplicates_array(){
  Collection<String[]> actual=newArrayList(array("Luke","Yoda"),array("Luke","Yoda"));
  try {
    iterables.assertDoesNotHaveDuplicates(someInfo(),actual);
  }
 catch (  AssertionError e) {
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
