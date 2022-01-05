@Test public void testGetRepos() throws Exception {
  Map<String,List<RepositoryInfo>> repos=metaInfo.getRepository(STACK_NAME_HDP,STACK_VERSION_HDP);
  Set<String> centos5Cnt=new HashSet<String>();
  Set<String> centos6Cnt=new HashSet<String>();
  Set<String> redhat6cnt=new HashSet<String>();
  for (  List<RepositoryInfo> vals : repos.values()) {
    for (    RepositoryInfo repo : vals) {
      LOG.debug("Dumping repo info : " + repo.toString());
      if (repo.getOsType().equals("centos5")) {
        centos5Cnt.add(repo.getRepoId());
      }
 else       if (repo.getOsType().equals("centos6")) {
        centos6Cnt.add(repo.getRepoId());
      }
 else       if (repo.getOsType().equals("redhat6")) {
        redhat6cnt.add(repo.getRepoId());
      }
 else {
        fail("Found invalid os" + repo.getOsType());
      }
      if (repo.getRepoId().equals("epel")) {
        assertFalse(repo.getMirrorsList().isEmpty());
        assertNull(repo.getBaseUrl());
      }
 else {
        assertNull(repo.getMirrorsList());
        assertFalse(repo.getBaseUrl().isEmpty());
      }
    }
  }
  assertEquals(3,centos5Cnt.size());
  assertEquals(3,redhat6cnt.size());
  assertEquals(3,centos6Cnt.size());
}
