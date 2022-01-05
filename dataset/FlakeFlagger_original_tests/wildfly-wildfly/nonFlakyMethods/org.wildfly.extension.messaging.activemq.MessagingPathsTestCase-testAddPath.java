@Test public void testAddPath() throws Exception {
  PathManagerService pathManagerService=new PathManagerService(){
{
      super.addHardcodedAbsolutePath(container,ServerEnvironment.SERVER_DATA_DIR,MY_SERVER_DATA_DIR);
      super.addHardcodedAbsolutePath(container,MY_PAGING_RELATIVE_TO,MY_PAGING_RELATIVE_TO_DIR);
    }
  }
;
  ActiveMQServerService.PathConfig pathConfig=new ActiveMQServerService.PathConfig(MY_ABSOLUTE_BINDINGS_DIR,DEFAULT_RELATIVE_TO,MY_RELATIVE_JOURNAL_DIR,DEFAULT_RELATIVE_TO,DEFAULT_LARGE_MESSAGE_DIR,DEFAULT_RELATIVE_TO,DEFAULT_PAGING_DIR,MY_PAGING_RELATIVE_TO);
  String resolvedJournalPath=pathConfig.resolveJournalPath(pathManagerService);
  assertTrue("the specific relative path must be prepended by the resolved default relative-to, resolvedJournalPath=" + resolvedJournalPath + ", MY_SERVER_DATA_DIR"+ MY_SERVER_DATA_DIR,resolvedJournalPath.startsWith(MY_SERVER_DATA_DIR));
  assertTrue(resolvedJournalPath.endsWith(MY_RELATIVE_JOURNAL_DIR));
  String resolvedBindingsPath=pathConfig.resolveBindingsPath(pathManagerService);
  assertEquals("the speficic absolute path must not be prepended by the resolved default relative-to, resolvedBindingsPath=" + resolvedBindingsPath,MY_ABSOLUTE_BINDINGS_DIR,resolvedBindingsPath);
  String resolvedPagingPath=pathConfig.resolvePagingPath(pathManagerService);
  assertTrue("the default path must be prepended by the resolved specific relative-to, resolvedPagingPath=" + resolvedPagingPath,resolvedPagingPath.startsWith(MY_PAGING_RELATIVE_TO_DIR));
  assertTrue(resolvedPagingPath.endsWith(DEFAULT_PAGING_DIR));
  String resolvedLargeMessagePath=pathConfig.resolveLargeMessagePath(pathManagerService);
  assertTrue("by default, the default path MUST prepended by the resolved default relative-to, resolvedLargeMessagePath=" + resolvedLargeMessagePath,resolvedLargeMessagePath.startsWith(MY_SERVER_DATA_DIR));
  assertTrue(resolvedLargeMessagePath.endsWith(DEFAULT_LARGE_MESSAGE_DIR));
}
