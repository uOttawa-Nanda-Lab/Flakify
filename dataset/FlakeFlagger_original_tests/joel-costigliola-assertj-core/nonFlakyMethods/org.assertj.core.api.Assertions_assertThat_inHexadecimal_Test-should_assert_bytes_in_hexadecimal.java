@Test public void should_assert_bytes_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x0[1]]> but was:<[0x0[2, 0x03]]>");
  assertThat(new byte[]{2,3}).inHexadecimal().isEqualTo(new byte[]{1});
}
