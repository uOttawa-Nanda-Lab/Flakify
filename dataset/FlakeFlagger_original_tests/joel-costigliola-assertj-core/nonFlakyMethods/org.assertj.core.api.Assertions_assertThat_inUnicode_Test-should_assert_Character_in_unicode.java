@Test public void should_assert_Character_in_unicode(){
  thrown.expectMessage("expected:<[\\u00f3]> but was:<[o]>");
  assertThat('o').inUnicode().isEqualTo('ó');
}
