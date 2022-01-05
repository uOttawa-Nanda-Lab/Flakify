@Test public void should_throw_exception_if_no_object_is_given() throws Exception {
  thrown.expect(IllegalArgumentException.class);
  idExtractor().extract(null);
}
