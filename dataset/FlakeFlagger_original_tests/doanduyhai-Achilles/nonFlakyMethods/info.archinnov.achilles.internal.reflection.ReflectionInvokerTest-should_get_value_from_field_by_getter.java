@Test public void should_get_value_from_field_by_getter() throws Exception {
  Bean bean=new Bean();
  bean.setComplicatedAttributeName("test");
  Method getter=Bean.class.getDeclaredMethod("getComplicatedAttributeName");
  String value=(String)invoker.getValueFromField(bean,getter);
  assertThat(value).isEqualTo("test");
}
