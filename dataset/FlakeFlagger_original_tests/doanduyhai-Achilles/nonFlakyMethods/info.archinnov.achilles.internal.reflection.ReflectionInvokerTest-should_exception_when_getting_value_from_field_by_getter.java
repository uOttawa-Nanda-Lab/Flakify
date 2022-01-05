@Test public void should_exception_when_getting_value_from_field_by_getter() throws Exception {
  Bean bean=new Bean();
  bean.setComplicatedAttributeName("test");
  Method getter=Bean.class.getDeclaredMethod("getComplicatedAttributeName");
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot invoke '" + getter.getName() + "' of type '"+ Bean.class.getCanonicalName()+ "' on instance 'bean'");
  invoker.getValueFromField("bean",getter);
}
