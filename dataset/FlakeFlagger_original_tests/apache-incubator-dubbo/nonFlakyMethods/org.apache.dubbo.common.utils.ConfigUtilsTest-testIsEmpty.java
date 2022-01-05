@Test public void testIsEmpty() throws Exception {
  assertThat(ConfigUtils.isEmpty(null),is(true));
  assertThat(ConfigUtils.isEmpty(""),is(true));
  assertThat(ConfigUtils.isEmpty("false"),is(true));
  assertThat(ConfigUtils.isEmpty("FALSE"),is(true));
  assertThat(ConfigUtils.isEmpty("0"),is(true));
  assertThat(ConfigUtils.isEmpty("null"),is(true));
  assertThat(ConfigUtils.isEmpty("NULL"),is(true));
  assertThat(ConfigUtils.isEmpty("n/a"),is(true));
  assertThat(ConfigUtils.isEmpty("N/A"),is(true));
}
