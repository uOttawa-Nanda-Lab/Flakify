@Test public void should_assert_byte_in_hexadecimal(){
  thrown.expectMessage("expected:<0x0[3]> but was:<0x0[2]>");
  assertThat((byte)2).inHexadecimal().isEqualTo((byte)3);
}
