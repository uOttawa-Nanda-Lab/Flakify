@Test public void should_fail_if_actual_is_null(){
  Class<?>[] types=new Class[]{File.class,Person.class,String.class};
  thrown.expectAssertionError(actualIsNull());
  objects.assertIsNotOfAnyClassIn(someInfo(),null,types);
}
