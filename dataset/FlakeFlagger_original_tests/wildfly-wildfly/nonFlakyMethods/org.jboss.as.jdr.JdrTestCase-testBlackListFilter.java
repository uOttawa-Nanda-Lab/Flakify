@Test public void testBlackListFilter(){
  VirtualFileFilter blf=Filters.regexBlackList();
  assertFalse(blf.accepts(VFS.getChild("/foo/bar/baz/mgmt-users.properties")));
  assertFalse(blf.accepts(VFS.getChild("/foo/bar/baz/application-users.properties")));
}
