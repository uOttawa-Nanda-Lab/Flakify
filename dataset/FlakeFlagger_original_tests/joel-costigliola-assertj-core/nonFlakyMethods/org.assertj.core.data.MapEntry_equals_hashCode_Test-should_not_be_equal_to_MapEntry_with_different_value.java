@Test public void should_not_be_equal_to_MapEntry_with_different_value(){
  assertFalse(entry.equals(entry("key0","value0")));
}
