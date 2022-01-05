@Test public void should_fail_if_actual_does_not_contains_every_expected_entries() throws Exception {
  AssertionInfo info=someInfo();
  String[] expectedKeys={"name","color"};
  Map<String,String> underTest=(Map<String,String>)Maps.mapOf(entry("name","Yoda"));
  try {
    maps.assertContainsOnlyKeys(info,underTest,expectedKeys);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyKeys(underTest,expectedKeys,newHashSet("color"),emptySet()));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
