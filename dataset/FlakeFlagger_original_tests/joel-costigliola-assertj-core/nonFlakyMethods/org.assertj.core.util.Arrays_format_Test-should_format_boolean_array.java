@Test public void should_format_boolean_array(){
  Object array=new boolean[]{true,false};
  assertEquals("[true, false]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[true, false]",Arrays.format(array));
}
