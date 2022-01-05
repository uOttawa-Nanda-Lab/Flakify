@SuppressWarnings("unchecked") @Test public void should_fail_if_given_entries_array_is_null() throws Exception {
  thrown.expectNullPointerException(entriesToLookForIsNull());
  maps.assertContainsExactly(someInfo(),linkedActual,(MapEntry[])null);
}
