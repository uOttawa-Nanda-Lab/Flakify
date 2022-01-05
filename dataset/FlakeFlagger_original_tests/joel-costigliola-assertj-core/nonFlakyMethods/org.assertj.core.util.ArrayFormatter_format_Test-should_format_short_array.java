@Test public void should_format_short_array(){
  assertEquals("[6, 8]",formatter.format(new StandardRepresentation(),new short[]{6,8}));
}
