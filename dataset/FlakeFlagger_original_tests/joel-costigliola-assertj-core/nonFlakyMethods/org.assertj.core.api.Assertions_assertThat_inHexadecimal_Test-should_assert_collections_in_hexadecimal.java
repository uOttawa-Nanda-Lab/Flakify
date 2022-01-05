@Test public void should_assert_collections_in_hexadecimal(){
  thrown.expectMessage("expected:<[0x0000_000[3]]> but was:<[0x0000_000[1, 0x0000_0002]]>");
  assertThat(Arrays.asList(1,2)).inHexadecimal().isEqualTo(Arrays.asList(3));
}
