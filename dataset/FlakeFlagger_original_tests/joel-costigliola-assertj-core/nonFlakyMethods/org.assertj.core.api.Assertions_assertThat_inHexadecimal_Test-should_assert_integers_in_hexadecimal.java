@Test public void should_assert_integers_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x0000_000[]2]> but was:<[0x0000_000[1, 0x0000_000]2]>");
  assertThat(new int[]{1,2}).inHexadecimal().isEqualTo(new int[]{2});
}
