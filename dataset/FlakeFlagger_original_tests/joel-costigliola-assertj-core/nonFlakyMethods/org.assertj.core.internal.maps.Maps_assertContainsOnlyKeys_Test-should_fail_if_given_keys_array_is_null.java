@Test public void should_fail_if_given_keys_array_is_null() throws Exception {
  thrown.expectNullPointerException(keysToLookForIsNull());
  maps.assertContainsOnlyKeys(someInfo(),actual,(String[])null);
}
