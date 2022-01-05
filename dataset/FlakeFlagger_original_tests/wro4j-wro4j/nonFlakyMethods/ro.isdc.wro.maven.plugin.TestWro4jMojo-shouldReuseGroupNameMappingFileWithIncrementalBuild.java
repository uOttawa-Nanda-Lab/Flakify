@Test public void shouldReuseGroupNameMappingFileWithIncrementalBuild() throws Exception {
  final File groupNameMappingFile=WroUtil.createTempFile();
  final Resource g1Resource=spy(Resource.create("1.js"));
  try {
    final WroModel model=new WroModel();
    model.addGroup(new Group("g1").addResource(g1Resource));
    model.addGroup(new Group("g2").addResource(Resource.create("2.js")));
    victim=new Wro4jMojo(){
      @Override protected WroManagerFactory newWroManagerFactory() throws MojoExecutionException {
        final DefaultStandaloneContextAwareManagerFactory managerFactory=new DefaultStandaloneContextAwareManagerFactory();
        managerFactory.setUriLocatorFactory(WroTestUtils.createResourceMockingLocatorFactory());
        managerFactory.setModelFactory(WroTestUtils.simpleModelFactory(model));
        managerFactory.setNamingStrategy(new DefaultHashEncoderNamingStrategy());
        return managerFactory;
      }
    }
;
    setUpMojo(victim);
    victim.setGroupNameMappingFile(groupNameMappingFile);
    when(mockBuildContext.isIncremental()).thenReturn(true);
    assertEquals(2,victim.getTargetGroupsAsList().size());
    victim.execute();
    final Properties groupNames=new Properties();
    groupNames.load(new FileInputStream(groupNameMappingFile));
    assertEquals(4,groupNames.entrySet().size());
    when(g1Resource.getUri()).thenReturn("1a.js");
    when(mockBuildContext.getValue(Mockito.eq("1a.js"))).thenAnswer(new Answer<Object>(){
      public Object answer(      final InvocationOnMock invocation) throws Throwable {
        final String key=(String)invocation.getArguments()[0];
        return victim.getManagerFactory().create().getHashStrategy().getHash(new ByteArrayInputStream(key.getBytes()));
      }
    }
);
    assertEquals(1,victim.getTargetGroupsAsList().size());
    victim.execute();
    groupNames.load(new FileInputStream(groupNameMappingFile));
    assertEquals(4,groupNames.entrySet().size());
  }
  finally {
    FileUtils.deleteQuietly(groupNameMappingFile);
  }
}
