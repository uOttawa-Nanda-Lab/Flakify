@Test public void should_assert_long_in_hexadecimal(){
  thrown.expectMessage("expected:<0x[8000_0000_0000_0000]> but was:<0x[7FFF_FFFF_FFFF_FFFF]>");
  assertThat(Long.MAX_VALUE).inHexadecimal().isEqualTo(Long.MIN_VALUE);
}
