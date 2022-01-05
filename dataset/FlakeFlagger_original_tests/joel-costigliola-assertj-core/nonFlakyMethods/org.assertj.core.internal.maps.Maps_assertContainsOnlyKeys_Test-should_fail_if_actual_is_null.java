@Test public void should_fail_if_actual_is_null() throws Exception {
  thrown.expectAssertionError(actualIsNull());
  maps.assertContainsOnlyKeys(someInfo(),null,"name");
}
