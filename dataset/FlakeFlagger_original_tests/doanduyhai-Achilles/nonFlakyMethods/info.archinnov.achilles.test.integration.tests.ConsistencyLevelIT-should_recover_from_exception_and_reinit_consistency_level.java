@Test public void should_recover_from_exception_and_reinit_consistency_level() throws Exception {
  boolean exceptionCaught=false;
  EntityWithWriteOneAndReadThreeConsistency bean=new EntityWithWriteOneAndReadThreeConsistency(id,"FN","LN");
  try {
    manager.persist(bean);
    manager.find(EntityWithWriteOneAndReadThreeConsistency.class,id);
  }
 catch (  UnavailableException e) {
    exceptionCaught=true;
  }
  assertThat(exceptionCaught).isTrue();
  CompleteBean newBean=new CompleteBean();
  newBean.setId(id);
  newBean.setName("name");
  manager.persist(newBean);
  newBean=manager.find(CompleteBean.class,newBean.getId());
  assertThat(newBean).isNotNull();
  assertThat(newBean.getName()).isEqualTo("name");
}
