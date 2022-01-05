@Test public void should_assert_float_in_binary(){
  thrown.expectMessage("expected:<0b01000000_0[1000000_00000000_0000000]0> but was:<0b01000000_0[0000110_01100110_0110011]0>");
  assertThat(2.1f).inBinary().isEqualTo(3f);
}
