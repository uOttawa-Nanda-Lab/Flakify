@Test public void should_assert_shorts_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x000[3]]> but was:<[0x000[1, 0x0002]]>");
  assertThat(new short[]{(short)1,(short)2}).inHexadecimal().isEqualTo(new short[]{(short)3});
}
