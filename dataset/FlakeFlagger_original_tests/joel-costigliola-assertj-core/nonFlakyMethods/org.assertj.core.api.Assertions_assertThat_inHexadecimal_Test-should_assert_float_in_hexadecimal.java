@Test public void should_assert_float_in_hexadecimal(){
  thrown.expectMessage("expected:<0x40[13_3333]> but was:<0x40[89_999A]>");
  assertThat(4.3f).inHexadecimal().isEqualTo(2.3f);
}
