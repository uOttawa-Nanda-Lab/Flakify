@Test public void should_format_byte_array(){
  assertEquals("[6, 8]",formatter.format(new StandardRepresentation(),new byte[]{6,8}));
}
