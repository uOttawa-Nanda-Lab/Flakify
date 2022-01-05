@Test public void testParseQueryString() throws Exception {
  assertThat(StringUtils.getQueryStringValue("key1=value1&key2=value2","key1"),equalTo("value1"));
  assertThat(StringUtils.getQueryStringValue("key1=value1&key2=value2","key2"),equalTo("value2"));
  assertThat(StringUtils.getQueryStringValue("","key1"),isEmptyOrNullString());
}
