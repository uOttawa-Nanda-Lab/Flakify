@Test public void should_fail_if_objects_are_not_mutually_comparable(){
  thrown.expect(ClassCastException.class);
  assertFalse(caseInsensitiveComparisonStrategy.areEqual("Yoda",5));
}
