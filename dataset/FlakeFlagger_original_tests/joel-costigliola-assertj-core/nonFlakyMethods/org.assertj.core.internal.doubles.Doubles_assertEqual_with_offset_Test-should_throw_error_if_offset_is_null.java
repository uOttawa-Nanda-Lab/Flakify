@Test public void should_throw_error_if_offset_is_null(){
  thrown.expectNullPointerException(offsetIsNull());
  doubles.assertEqual(someInfo(),new Double(8d),new Double(8d),null);
}
