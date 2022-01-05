@Test public void testIsContains() throws Exception {
  assertThat(StringUtils.isContains("a,b, c","b"),is(true));
  assertThat(StringUtils.isContains("","b"),is(false));
  assertThat(StringUtils.isContains(new String[]{"a","b","c"},"b"),is(true));
  assertThat(StringUtils.isContains((String[])null,null),is(false));
}
