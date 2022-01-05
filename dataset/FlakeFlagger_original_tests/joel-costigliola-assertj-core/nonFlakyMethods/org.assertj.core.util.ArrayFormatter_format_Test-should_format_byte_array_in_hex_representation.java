@Test public void should_format_byte_array_in_hex_representation(){
  assertEquals("[0x06, 0x08]",formatter.format(new HexadecimalRepresentation(),new byte[]{6,8}));
}
