@Test public void should_assert_doubles_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x[4008]_0000_0000_0000]> but was:<[0x[3FF0_0000_0000_0000, 0x4000]_0000_0000_0000]>");
  assertThat(new double[]{1d,2d}).inHexadecimal().isEqualTo(new double[]{3d});
}
