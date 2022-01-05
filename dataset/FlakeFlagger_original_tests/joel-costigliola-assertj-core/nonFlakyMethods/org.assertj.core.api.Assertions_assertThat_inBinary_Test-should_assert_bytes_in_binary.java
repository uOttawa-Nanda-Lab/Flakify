@Test public void should_assert_bytes_in_binary(){
  thrown.expectMessage("expected:<[0b000000[0]1]> but was:<[0b000000[10, 0b0000001]1]>");
  assertThat(new byte[]{2,3}).inBinary().isEqualTo(new byte[]{1});
}
