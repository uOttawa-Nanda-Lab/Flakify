/** 
 * Test for the following scenario: when incremental build is performed, only changed resources are processed. Given that there are two target groups, and a resource from only one group is changed incremental build should process only that one group. However, if the targetFolder does not exist, all target groups must be processed.
 */
@Test public void shouldProcessTargetGroupsWhenDestinationFolderDoesNotExist() throws Exception {
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
  final String constantHash="hash";
  when(mockHashStrategy.getHash(Mockito.any(InputStream.class))).thenReturn(constantHash);
  setUpMojo(victim);
  victim.setIgnoreMissingResources(true);
  final int totalGroups=9;
  assertEquals(totalGroups,victim.getTargetGroupsAsList().size());
  when(mockBuildContext.isIncremental()).thenReturn(true);
  when(mockBuildContext.getValue(Mockito.anyString())).thenReturn(constantHash);
  assertEquals(0,victim.getTargetGroupsAsList().size());
  destinationFolder.delete();
  assertEquals(9,victim.getTargetGroupsAsList().size());
  victim.doExecute();
}
