@Test public void should_fail_if_actual_contains_unexpected_entry() throws Exception {
  AssertionInfo info=someInfo();
  String[] expectedKeys={"name"};
  try {
    maps.assertContainsOnlyKeys(info,actual,expectedKeys);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyKeys(actual,expectedKeys,emptySet(),newHashSet("color")));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
