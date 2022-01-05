@Test public void should_throw_error_if_index_is_null(){
  thrown.expectNullPointerException("Index should not be null");
  lists.assertDoesNotContain(someInfo(),actual,"Yoda",null);
}
