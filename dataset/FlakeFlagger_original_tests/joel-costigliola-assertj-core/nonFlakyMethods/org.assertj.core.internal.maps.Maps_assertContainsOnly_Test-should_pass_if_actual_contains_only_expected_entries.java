@SuppressWarnings("unchecked") @Test public void should_pass_if_actual_contains_only_expected_entries() throws Exception {
  maps.assertContainsOnly(someInfo(),actual,entry("name","Yoda"),entry("color","green"));
}
