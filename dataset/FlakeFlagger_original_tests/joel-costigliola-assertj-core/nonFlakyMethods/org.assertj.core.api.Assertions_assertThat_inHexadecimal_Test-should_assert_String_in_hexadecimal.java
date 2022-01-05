@Test public void should_assert_String_in_hexadecimal(){
  thrown.expectMessage("expected:<\"['0x0061', '0x00[62]', '0x0063']\"> but was:<\"['0x0061', '0x00[36]', '0x0063']\">");
  assertThat("a6c").inHexadecimal().isEqualTo("abc");
}
