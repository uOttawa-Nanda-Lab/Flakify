@Test public void should_exception_when_setting_value_to_field() throws Exception {
  Bean bean=new Bean();
  bean.setComplicatedAttributeName("test");
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot set value to field '" + field.getName() + "' of type '"+ field.getType().getCanonicalName()+ "' on instance 'bean'");
  invoker.setValueToField("bean",field,"test");
}
