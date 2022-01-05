@Test public void should_unproxy_real_entryset() throws Exception {
  Map<Integer,CompleteBean> map=new HashMap<Integer,CompleteBean>();
  CompleteBean completeBean=new CompleteBean();
  map.put(1,completeBean);
  Map.Entry<Integer,CompleteBean> entry=map.entrySet().iterator().next();
  when(proxifier.isProxy(completeBean)).thenReturn(false);
  Map.Entry<Integer,CompleteBean> actual=proxifier.removeProxy(entry);
  assertThat(actual).isSameAs(entry);
  assertThat(actual.getValue()).isSameAs(completeBean);
}
