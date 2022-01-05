@Test public void should_format_longs_and_integers(){
  assertFalse(new StandardRepresentation().toStringOf(20L).equals(toStringOf(20)));
  assertEquals("20",toStringOf(20));
  assertEquals("20L",toStringOf(20L));
}
