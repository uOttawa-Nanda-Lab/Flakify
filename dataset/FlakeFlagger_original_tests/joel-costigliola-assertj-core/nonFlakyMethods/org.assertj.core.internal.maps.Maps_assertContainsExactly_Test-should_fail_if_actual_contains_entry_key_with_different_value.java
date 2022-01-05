@Test public void should_fail_if_actual_contains_entry_key_with_different_value() throws Exception {
  AssertionInfo info=someInfo();
  MapEntry<String,String>[] expectedEntries=array(entry("name","Yoda"),entry("color","yellow"));
  try {
    maps.assertContainsExactly(info,actual,expectedEntries);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainExactly(actual,expectedEntries,newHashSet(entry("color","yellow")),newHashSet(entry("color","green"))));
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
