@Test public void should_exception_when_getting_value_from_field() throws Exception {
  Bean bean=new Bean();
  bean.setComplicatedAttributeName("test");
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot get value from field '" + field.getName() + "' of type '"+ Bean.class.getCanonicalName()+ "' on instance 'bean'");
  invoker.getValueFromField("bean",field);
}
