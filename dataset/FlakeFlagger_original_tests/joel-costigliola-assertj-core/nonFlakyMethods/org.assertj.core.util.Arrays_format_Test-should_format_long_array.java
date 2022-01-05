@Test public void should_format_long_array(){
  Object array=new long[]{160l,98l};
  assertEquals("[160L, 98L]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[160L, 98L]",Arrays.format(array));
}
