@Test public void should_exception_when_setting_null_to_primitive_type() throws Exception {
  Field field=BeanWithPrimitive.class.getDeclaredField("count");
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot set null value to primitive type 'int' of field 'count' on instance of class'" + BeanWithPrimitive.class.getCanonicalName() + "'");
  invoker.setValueToField(new BeanWithPrimitive(),field,null);
}
