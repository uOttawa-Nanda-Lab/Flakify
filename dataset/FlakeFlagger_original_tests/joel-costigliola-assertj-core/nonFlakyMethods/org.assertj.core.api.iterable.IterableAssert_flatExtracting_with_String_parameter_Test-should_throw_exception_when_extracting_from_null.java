@Test public void should_throw_exception_when_extracting_from_null(){
  thrown.expect(IllegalArgumentException.class);
  assertThat(newArrayList(homer,null)).flatExtracting("children");
}
