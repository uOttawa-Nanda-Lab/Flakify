@Test public void should_assert_floats_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x408[3_3333]]> but was:<[0x408[9_999A, 0xC000_0000]]>");
  assertThat(new float[]{4.3f,-2f}).inHexadecimal().isEqualTo(new float[]{4.1f});
}
