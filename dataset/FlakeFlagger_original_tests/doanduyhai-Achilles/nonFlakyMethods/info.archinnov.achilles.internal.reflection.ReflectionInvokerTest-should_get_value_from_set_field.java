@Test public void should_get_value_from_set_field() throws Exception {
  CompleteBean bean=new CompleteBean();
  bean.setFollowers(Sets.newHashSet("foo","bar"));
  Field field=CompleteBean.class.getDeclaredField("followers");
  Set<String> value=invoker.getSetValueFromField(bean,field);
  assertThat(value).containsOnly("foo","bar");
}
