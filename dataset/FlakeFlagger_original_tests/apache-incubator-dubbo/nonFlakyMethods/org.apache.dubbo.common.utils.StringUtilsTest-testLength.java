@Test public void testLength() throws Exception {
  assertThat(StringUtils.length(null),equalTo(0));
  assertThat(StringUtils.length("abc"),equalTo(3));
}
