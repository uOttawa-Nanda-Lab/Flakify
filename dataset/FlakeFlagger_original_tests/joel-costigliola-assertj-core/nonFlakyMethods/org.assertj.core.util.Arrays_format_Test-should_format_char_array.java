@Test public void should_format_char_array(){
  Object array=new char[]{'a','b'};
  assertEquals("['a', 'b']",Arrays.format(new StandardRepresentation(),array));
  assertEquals("['a', 'b']",Arrays.format(array));
}
