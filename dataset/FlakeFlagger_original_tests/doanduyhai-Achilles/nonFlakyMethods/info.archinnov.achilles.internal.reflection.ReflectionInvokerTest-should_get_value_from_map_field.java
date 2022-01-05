@Test public void should_get_value_from_map_field() throws Exception {
  CompleteBean bean=new CompleteBean();
  bean.setPreferences(ImmutableMap.of(1,"FR"));
  Field field=CompleteBean.class.getDeclaredField("preferences");
  Map<Integer,String> value=invoker.getMapValueFromField(bean,field);
  assertThat(value).containsKey(1).containsValue("FR");
}
