@Test public void should_throw_error_if_index_is_negative(){
  thrown.expectIndexOutOfBoundsException("Index should be between 0 and 1 (inclusive,) but was -1");
  list.get(-1);
}
