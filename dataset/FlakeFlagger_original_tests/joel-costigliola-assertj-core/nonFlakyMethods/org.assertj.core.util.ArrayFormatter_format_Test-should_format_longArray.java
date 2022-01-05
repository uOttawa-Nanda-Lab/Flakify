@Test public void should_format_longArray(){
  assertEquals("[6L, 8L]",formatter.format(new StandardRepresentation(),new long[]{6l,8l}));
}
