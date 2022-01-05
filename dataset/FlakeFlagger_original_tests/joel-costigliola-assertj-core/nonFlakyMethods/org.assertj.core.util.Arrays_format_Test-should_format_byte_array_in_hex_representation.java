@Test public void should_format_byte_array_in_hex_representation(){
  Object array=new byte[]{(byte)3,(byte)8};
  assertEquals("[0x03, 0x08]",Arrays.format(new HexadecimalRepresentation(),array));
}
