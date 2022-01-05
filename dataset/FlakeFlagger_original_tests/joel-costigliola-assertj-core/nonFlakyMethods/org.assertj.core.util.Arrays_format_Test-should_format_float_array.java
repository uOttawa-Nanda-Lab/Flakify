@Test public void should_format_float_array(){
  Object array=new float[]{6.1f,8.6f};
  assertEquals("[6.1f, 8.6f]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[6.1f, 8.6f]",Arrays.format(array));
}
