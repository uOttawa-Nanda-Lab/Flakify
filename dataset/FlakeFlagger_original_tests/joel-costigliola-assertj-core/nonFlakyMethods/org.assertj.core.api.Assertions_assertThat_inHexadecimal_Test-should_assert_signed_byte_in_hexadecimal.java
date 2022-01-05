@Test public void should_assert_signed_byte_in_hexadecimal(){
  thrown.expectMessage("expected:<0x[03]> but was:<0x[FE]>");
  assertThat((byte)-2).inHexadecimal().isEqualTo((byte)3);
}
