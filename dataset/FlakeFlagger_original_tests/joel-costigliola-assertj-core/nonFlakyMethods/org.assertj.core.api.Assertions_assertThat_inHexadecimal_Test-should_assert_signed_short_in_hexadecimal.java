@Test public void should_assert_signed_short_in_hexadecimal(){
  thrown.expectMessage("expected:<0x[0003]> but was:<0x[FFFE]>");
  assertThat((short)-2).inHexadecimal().isEqualTo((short)3);
}
