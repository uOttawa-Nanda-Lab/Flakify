@Test public void should_format_bytes_as_hex(){
  assertFalse(toStringOf((byte)20).equals(toStringOf((char)20)));
  assertEquals(toStringOf((byte)20),(toStringOf((short)20)));
  assertEquals("32",toStringOf((byte)32));
}
