@Test public void should_throw_null_pointer_exception_when_extracting_from_null() throws Exception {
  thrown.expect(NullPointerException.class);
  assertThat(newArrayList(homer,null)).flatExtracting(children);
}
