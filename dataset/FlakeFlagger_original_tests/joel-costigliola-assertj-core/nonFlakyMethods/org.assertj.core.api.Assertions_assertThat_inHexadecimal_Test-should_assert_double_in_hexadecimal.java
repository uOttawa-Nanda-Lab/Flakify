@Test public void should_assert_double_in_hexadecimal(){
  thrown.expectMessage("expected:<0x40[02_6666_6666_6666]> but was:<0x40[11_3333_3333_3333]>");
  assertThat(4.3d).inHexadecimal().isEqualTo(2.3d);
}
