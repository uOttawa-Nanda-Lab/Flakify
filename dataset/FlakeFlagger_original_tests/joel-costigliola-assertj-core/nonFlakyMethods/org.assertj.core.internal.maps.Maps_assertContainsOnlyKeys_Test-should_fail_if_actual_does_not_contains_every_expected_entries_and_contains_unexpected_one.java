@Test public void should_fail_if_actual_does_not_contains_every_expected_entries_and_contains_unexpected_one() throws Exception {
  AssertionInfo info=someInfo();
  String[] expectedKeys={"name","color"};
  Map<String,String> underTest=(Map<String,String>)Maps.mapOf(entry("name","Yoda"),entry("job","Jedi"));
  try {
    maps.assertContainsOnlyKeys(info,underTest,expectedKeys);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyKeys(underTest,expectedKeys,newHashSet("color"),newHashSet("job")));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
