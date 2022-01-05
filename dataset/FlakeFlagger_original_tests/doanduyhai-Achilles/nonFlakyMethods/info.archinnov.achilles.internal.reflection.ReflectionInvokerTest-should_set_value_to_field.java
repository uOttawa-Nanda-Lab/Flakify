@Test public void should_set_value_to_field() throws Exception {
  Bean bean=new Bean();
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  invoker.setValueToField(bean,field,"fecezzef");
  assertThat(bean.getComplicatedAttributeName()).isEqualTo("fecezzef");
}
