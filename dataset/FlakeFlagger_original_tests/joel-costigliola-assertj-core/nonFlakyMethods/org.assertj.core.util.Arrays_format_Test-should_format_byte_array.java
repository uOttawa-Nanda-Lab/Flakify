@Test public void should_format_byte_array(){
  Object array=new byte[]{(byte)3,(byte)8};
  assertEquals("[3, 8]",Arrays.format(new StandardRepresentation(),array));
  assertEquals("[3, 8]",Arrays.format(array));
}
