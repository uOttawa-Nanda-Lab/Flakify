@Test public void should_get_value_from_list_field() throws Exception {
  CompleteBean bean=new CompleteBean();
  bean.setFriends(Arrays.asList("foo","bar"));
  Field field=CompleteBean.class.getDeclaredField("friends");
  List<String> value=invoker.getListValueFromField(bean,field);
  assertThat(value).containsExactly("foo","bar");
}
