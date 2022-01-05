@Test public void should_fail_if_given_keys_array_is_empty() throws Exception {
  thrown.expectIllegalArgumentException(keysToLookForIsEmpty());
  maps.assertContainsOnlyKeys(someInfo(),actual,emptyKeys());
}
