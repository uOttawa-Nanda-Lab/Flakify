@Test public void shouldDetectIncrementalChange() throws Exception {
  victim=new Wro4jMojo(){
    @Override protected WroManagerFactory getManagerFactory(){
      return new WroManagerFactoryDecorator(super.getManagerFactory()){
        @Override protected void onBeforeBuild(        final Builder builder){
          builder.setHashStrategy(mockHashStrategy);
        }
      }
;
    }
  }
;
  setUpMojo(victim);
  final String hashValue="SomeHashValue";
  when(mockHashStrategy.getHash(Mockito.any(InputStream.class))).thenReturn(hashValue);
  when(mockBuildContext.isIncremental()).thenReturn(true);
  when(mockBuildContext.getValue(Mockito.anyString())).thenReturn(hashValue);
  victim.setIgnoreMissingResources(true);
  assertTrue(victim.getTargetGroupsAsList().isEmpty());
  when(mockHashStrategy.getHash(Mockito.any(InputStream.class))).thenReturn("TotallyDifferentValue");
  assertFalse(victim.getTargetGroupsAsList().isEmpty());
}
