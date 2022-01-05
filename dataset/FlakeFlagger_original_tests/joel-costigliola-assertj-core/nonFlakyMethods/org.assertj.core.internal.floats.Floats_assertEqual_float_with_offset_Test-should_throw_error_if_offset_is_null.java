@Test public void should_throw_error_if_offset_is_null(){
  thrown.expectNullPointerException(offsetIsNull());
  floats.assertEqual(someInfo(),new Float(8f),8f,null);
}
