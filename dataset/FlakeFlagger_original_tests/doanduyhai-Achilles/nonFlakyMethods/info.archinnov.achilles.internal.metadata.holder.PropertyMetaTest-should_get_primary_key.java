@Test public void should_get_primary_key() throws Exception {
  long id=RandomUtils.nextLong();
  CompleteBean entity=new CompleteBean(id);
  PropertyMeta pm=new PropertyMeta();
  pm.setType(ID);
  pm.setInvoker(invoker);
  when(invoker.getPrimaryKey(entity,pm)).thenReturn(id);
  assertThat(pm.getPrimaryKey(entity)).isEqualTo(id);
}
