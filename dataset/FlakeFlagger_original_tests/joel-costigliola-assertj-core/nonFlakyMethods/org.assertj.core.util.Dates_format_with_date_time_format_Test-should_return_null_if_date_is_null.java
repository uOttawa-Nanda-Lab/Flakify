@Test public void should_return_null_if_date_is_null(){
  assertNull(formatAsDatetime((Date)null));
}
