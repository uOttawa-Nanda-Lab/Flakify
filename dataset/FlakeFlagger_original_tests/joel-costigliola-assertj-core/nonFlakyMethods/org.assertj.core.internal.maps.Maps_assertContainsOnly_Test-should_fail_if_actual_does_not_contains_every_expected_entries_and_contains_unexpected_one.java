@Test public void should_fail_if_actual_does_not_contains_every_expected_entries_and_contains_unexpected_one() throws Exception {
  AssertionInfo info=someInfo();
  MapEntry<String,String>[] expected=array(entry("name","Yoda"),entry("color","green"));
  Map<String,String> underTest=Maps.mapOf(entry("name","Yoda"),entry("job","Jedi"));
  try {
    maps.assertContainsOnly(info,underTest,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(underTest,expected,newHashSet(entry("color","green")),newHashSet(entry("job","Jedi"))));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
