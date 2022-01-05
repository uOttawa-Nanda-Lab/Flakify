@SuppressWarnings("unchecked") @Test public void should_fail_if_given_entries_array_is_null() throws Exception {
  thrown.expectNullPointerException(entriesToLookForIsNull());
  maps.assertContainsOnly(someInfo(),actual,(MapEntry[])null);
}
