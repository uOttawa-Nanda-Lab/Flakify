@SuppressWarnings("unchecked") @Test public void should_pass_if_actual_and_entries_are_empty() throws Exception {
  maps.assertContainsOnly(someInfo(),emptyMap(),emptyEntries());
}
