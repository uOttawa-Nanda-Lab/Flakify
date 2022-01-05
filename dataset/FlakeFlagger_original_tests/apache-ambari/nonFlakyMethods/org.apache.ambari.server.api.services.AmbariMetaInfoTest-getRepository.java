@Test public void getRepository(){
  Map<String,List<RepositoryInfo>> repository=metaInfo.getRepository(STACK_NAME_HDP,STACK_VERSION_HDP);
  assertNotNull(repository);
  assertFalse(repository.get("centos5").isEmpty());
  assertFalse(repository.get("centos6").isEmpty());
}
