@Test public void should_format_double_array(){
  Object array=new double[]{6.8,8.3};
  assertEquals("[6.8, 8.3]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[6.8, 8.3]",Arrays.format(array));
}
