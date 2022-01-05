@Test public void testIsNumeric() throws Exception {
  assertThat(StringUtils.isNumeric("123"),is(true));
  assertThat(StringUtils.isNumeric("1a3"),is(false));
  assertThat(StringUtils.isNumeric(null),is(false));
}
