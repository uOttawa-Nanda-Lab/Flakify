@Test public void should_return_null_if_iterable_is_null(){
  Iterable<?> elements=null;
  assertNull(Lists.newArrayList(elements));
}
