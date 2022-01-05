@Test public void should_format_int_array(){
  assertEquals("[6, 8]",formatter.format(new StandardRepresentation(),new int[]{6,8}));
}
