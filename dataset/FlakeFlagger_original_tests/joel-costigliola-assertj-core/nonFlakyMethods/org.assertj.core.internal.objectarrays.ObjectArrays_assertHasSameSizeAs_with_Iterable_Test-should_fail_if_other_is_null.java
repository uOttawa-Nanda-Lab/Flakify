@Test public void should_fail_if_other_is_null(){
  thrown.expectNullPointerException("The Iterable to compare actual size with should not be null");
  String[] actual=array("Solo","Leia");
  Iterable<?> other=null;
  arrays.assertHasSameSizeAs(someInfo(),actual,other);
}
