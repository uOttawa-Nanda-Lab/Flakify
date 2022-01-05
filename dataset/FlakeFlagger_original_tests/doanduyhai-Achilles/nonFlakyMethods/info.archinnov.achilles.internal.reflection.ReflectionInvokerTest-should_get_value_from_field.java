@Test public void should_get_value_from_field() throws Exception {
  Bean bean=new Bean();
  bean.setComplicatedAttributeName("test");
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  String value=invoker.getValueFromField(bean,field);
  assertThat(value).isEqualTo("test");
}
