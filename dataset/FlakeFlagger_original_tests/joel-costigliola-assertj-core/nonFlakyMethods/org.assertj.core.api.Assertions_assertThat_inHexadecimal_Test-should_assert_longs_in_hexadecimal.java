@Test public void should_assert_longs_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x[0000_0000_0000_0003]]> but was:<[0x[FFFF_FFFF_FFFF_FFFF, 0x0000_0000_0000_0002]]>");
  assertThat(new long[]{-1L,2L}).inHexadecimal().isEqualTo(new long[]{3L});
}
