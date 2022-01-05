@Test public void should_return_correct_size_for_non_collection_iterable(){
  Iterable<Throwable> sqlException=new SQLException(new Exception(new Exception()));
  assertEquals(3,Iterables.sizeOf(sqlException));
}
