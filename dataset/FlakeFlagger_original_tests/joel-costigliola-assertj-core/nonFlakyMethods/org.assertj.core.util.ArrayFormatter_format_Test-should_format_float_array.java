@Test public void should_format_float_array(){
  assertEquals("[6.0f, 8.0f]",formatter.format(new StandardRepresentation(),new float[]{6f,8f}));
}
