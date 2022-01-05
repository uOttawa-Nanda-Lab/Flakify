@Test public void should_format_int_array(){
  Object array=new int[]{78,66};
  assertEquals("[78, 66]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[78, 66]",Arrays.format(array));
}
