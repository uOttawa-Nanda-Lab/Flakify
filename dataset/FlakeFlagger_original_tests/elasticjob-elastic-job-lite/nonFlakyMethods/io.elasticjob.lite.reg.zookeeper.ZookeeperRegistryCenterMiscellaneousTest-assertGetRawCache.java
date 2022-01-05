@Test public void assertGetRawCache(){
  assertThat(zkRegCenter.getRawCache("/test"),instanceOf(TreeCache.class));
}
