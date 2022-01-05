@Test public void should_assert_signed_long_in_hexadecimal(){
  thrown.expectMessage("expected:<0x[0000_0000_0000_0002]> but was:<0x[FFFF_FFFF_FFFF_FFFE]>");
  assertThat(-2L).inHexadecimal().isEqualTo(2L);
}
