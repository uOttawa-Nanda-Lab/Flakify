@Test public void should_format_doubles_and_floats(){
  assertFalse(toStringOf(20.0f).equals(toStringOf(20.0)));
  assertEquals("20.0",toStringOf(20.0));
  assertEquals("20.0f",toStringOf(20.0f));
}
