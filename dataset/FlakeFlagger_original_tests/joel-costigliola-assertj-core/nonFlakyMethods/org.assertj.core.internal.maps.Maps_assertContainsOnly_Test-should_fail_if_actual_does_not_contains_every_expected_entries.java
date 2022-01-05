@Test public void should_fail_if_actual_does_not_contains_every_expected_entries() throws Exception {
  AssertionInfo info=someInfo();
  MapEntry<String,String>[] expected=array(entry("name","Yoda"),entry("color","green"));
  Map<String,String> underTest=Maps.mapOf(entry("name","Yoda"));
  try {
    maps.assertContainsOnly(info,underTest,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(underTest,expected,newHashSet(entry("color","green")),emptySet()));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
