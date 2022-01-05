@Test public void assertTransformWithNull(){
  assertThat(ExceptionUtil.transform(null),is(""));
}
