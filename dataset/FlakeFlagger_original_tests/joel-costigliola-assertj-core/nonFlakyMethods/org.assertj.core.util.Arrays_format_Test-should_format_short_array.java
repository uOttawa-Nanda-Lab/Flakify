@Test public void should_format_short_array(){
  Object array=new short[]{(short)5,(short)8};
  assertEquals("[5, 8]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[5, 8]",Arrays.format(array));
}
