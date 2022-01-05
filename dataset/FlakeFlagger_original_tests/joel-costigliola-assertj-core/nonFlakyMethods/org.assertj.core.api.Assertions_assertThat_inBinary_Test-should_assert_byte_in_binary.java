@Test public void should_assert_byte_in_binary(){
  thrown.expectMessage("expected:<0b0000001[1]> but was:<0b0000001[0]>");
  assertThat((byte)2).inBinary().isEqualTo((byte)3);
}
