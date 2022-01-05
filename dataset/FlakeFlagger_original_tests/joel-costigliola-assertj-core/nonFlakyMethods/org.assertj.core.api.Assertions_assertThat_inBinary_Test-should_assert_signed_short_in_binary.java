@Test public void should_assert_signed_short_in_binary(){
  thrown.expectMessage("expected:<0b[00000000_000000]11> but was:<0b[11111111_111111]11>");
  assertThat((short)-1).inBinary().isEqualTo((short)3);
}
