@Test public void shouldGenerateGroupMappingUsingNoOpNamingStrategy() throws Exception {
  setWroWithValidResources();
  final File groupNameMappingFile=new File(FileUtils.getTempDirectory(),"groupMapping-" + new Date().getTime());
  victim.setGroupNameMappingFile(groupNameMappingFile);
  victim.setIgnoreMissingResources(true);
  victim.execute();
  final Properties groupNames=new Properties();
  groupNames.load(new FileInputStream(groupNameMappingFile));
  LOG.debug("groupNames: {}",groupNames);
  assertEquals("g1.js",groupNames.get("g1.js"));
  FileUtils.deleteQuietly(groupNameMappingFile);
}
