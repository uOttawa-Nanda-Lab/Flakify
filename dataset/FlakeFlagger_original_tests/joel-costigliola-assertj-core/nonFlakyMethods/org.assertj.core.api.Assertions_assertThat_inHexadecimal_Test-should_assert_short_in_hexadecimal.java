@Test public void should_assert_short_in_hexadecimal(){
  thrown.expectMessage("expected:<0x000[3]> but was:<0x000[2]>");
  assertThat((short)2).inHexadecimal().isEqualTo((short)3);
}
