@Test public void should_throw_error_if_index_is_greater_than_size(){
  thrown.expectIndexOutOfBoundsException("Index should be between 0 and 1 (inclusive,) but was 6");
  list.get(6);
}
