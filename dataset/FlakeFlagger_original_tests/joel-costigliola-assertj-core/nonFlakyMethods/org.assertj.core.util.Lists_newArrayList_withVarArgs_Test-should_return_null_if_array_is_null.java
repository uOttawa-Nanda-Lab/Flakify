@Test public void should_return_null_if_array_is_null(){
  Object[] elements=null;
  assertNull(Lists.newArrayList(elements));
}
