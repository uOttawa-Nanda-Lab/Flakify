@Test public void testParseInteger() throws Exception {
  assertThat(StringUtils.parseInteger(null),equalTo(0));
  assertThat(StringUtils.parseInteger("123"),equalTo(123));
}
