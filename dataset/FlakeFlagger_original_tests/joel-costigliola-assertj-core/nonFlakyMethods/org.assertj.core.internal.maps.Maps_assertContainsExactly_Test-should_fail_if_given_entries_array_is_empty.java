@SuppressWarnings("unchecked") @Test public void should_fail_if_given_entries_array_is_empty() throws Exception {
  thrown.expectIllegalArgumentException(entriesToLookForIsEmpty());
  maps.assertContainsExactly(someInfo(),linkedActual,emptyEntries());
}
