@Test public void testIsNotEmpty() throws Exception {
  assertThat(ConfigUtils.isNotEmpty("abc"),is(true));
}
