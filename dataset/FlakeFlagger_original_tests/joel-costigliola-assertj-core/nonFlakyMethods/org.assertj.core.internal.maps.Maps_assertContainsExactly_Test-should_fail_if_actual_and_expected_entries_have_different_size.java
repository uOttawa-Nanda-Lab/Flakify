@Test public void should_fail_if_actual_and_expected_entries_have_different_size() throws Exception {
  AssertionInfo info=someInfo();
  MapEntry<String,String>[] expected=array(entry("name","Yoda"));
  try {
    maps.assertContainsExactly(info,linkedActual,expected);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveSameSizeAs(linkedActual,linkedActual.size(),expected.length).create());
    return;
  }
  shouldHaveThrown(AssertionError.class);
}
