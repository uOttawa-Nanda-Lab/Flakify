@SuppressWarnings("unchecked") @Test public void should_fail_if_actual_contains_given_entries_in_disorder() throws Exception {
  AssertionInfo info=someInfo();
  try {
    maps.assertContainsExactly(info,linkedActual,entry("color","green"),entry("name","Yoda"));
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,elementsDifferAtIndex(entry("name","Yoda"),entry("color","green"),0));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
