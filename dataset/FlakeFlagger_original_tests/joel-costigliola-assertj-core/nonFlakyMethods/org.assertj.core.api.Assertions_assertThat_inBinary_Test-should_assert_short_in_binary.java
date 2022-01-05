@Test public void should_assert_short_in_binary(){
  thrown.expectMessage("expected:<0b00000000_0000001[1]> but was:<0b00000000_0000001[0]>");
  assertThat((short)2).inBinary().isEqualTo((short)3);
}
