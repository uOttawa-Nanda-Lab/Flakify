@Test public void should_not_set_value_when_null_field() throws Exception {
  Field field=Bean.class.getDeclaredField("complicatedAttributeName");
  invoker.setValueToField(null,field,"fecezzef");
}
