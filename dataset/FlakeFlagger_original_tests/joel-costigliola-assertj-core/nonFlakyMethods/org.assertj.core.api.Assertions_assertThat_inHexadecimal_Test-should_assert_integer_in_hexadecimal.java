@Test public void should_assert_integer_in_hexadecimal(){
  thrown.expectMessage("expected:<0x0000_000[3]> but was:<0x0000_000[2]>");
  assertThat(2).inHexadecimal().isEqualTo(3);
}
