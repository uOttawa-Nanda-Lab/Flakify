@Test public void should_format_double_array(){
  assertEquals("[6.0, 8.0]",formatter.format(new StandardRepresentation(),new double[]{6d,8d}));
}
