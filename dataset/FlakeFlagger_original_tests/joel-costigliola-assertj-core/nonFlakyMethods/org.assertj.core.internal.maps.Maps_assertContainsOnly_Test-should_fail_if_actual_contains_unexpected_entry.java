@Test public void should_fail_if_actual_contains_unexpected_entry() throws Exception {
  AssertionInfo info=someInfo();
  MapEntry<String,String>[] expected=array(entry("name","Yoda"));
  try {
    maps.assertContainsOnly(info,actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnly(actual,expected,emptySet(),newHashSet(entry("color","green"))));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
