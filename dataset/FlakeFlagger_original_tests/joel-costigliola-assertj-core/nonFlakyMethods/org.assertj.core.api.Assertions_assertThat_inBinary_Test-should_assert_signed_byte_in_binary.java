@Test public void should_assert_signed_byte_in_binary(){
  thrown.expectMessage("expected:<0b[00000011]> but was:<0b[11111110]>");
  assertThat((byte)-2).inBinary().isEqualTo((byte)3);
}
