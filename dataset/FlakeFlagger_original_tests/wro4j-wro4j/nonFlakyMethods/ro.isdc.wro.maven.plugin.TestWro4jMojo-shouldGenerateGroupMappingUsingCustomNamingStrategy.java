@Test public void shouldGenerateGroupMappingUsingCustomNamingStrategy() throws Exception {
  setWroWithValidResources();
  final File groupNameMappingFile=new File(FileUtils.getTempDirectory(),"groupMapping-" + new Date().getTime());
  victim.setWroManagerFactory(CustomNamingStrategyWroManagerFactory.class.getName());
  victim.setGroupNameMappingFile(groupNameMappingFile);
  victim.setIgnoreMissingResources(true);
  victim.execute();
  final Properties groupNames=new Properties();
  groupNames.load(new FileInputStream(groupNameMappingFile));
  LOG.debug("groupNames: {}",groupNames);
  Assert.assertEquals(CustomNamingStrategyWroManagerFactory.PREFIX + "g1.js",groupNames.get("g1.js"));
  FileUtils.deleteQuietly(groupNameMappingFile);
}
