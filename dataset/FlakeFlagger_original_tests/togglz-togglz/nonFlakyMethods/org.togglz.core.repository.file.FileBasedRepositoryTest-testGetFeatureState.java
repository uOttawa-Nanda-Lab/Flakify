@Test public void testGetFeatureState(){
  FileBasedStateRepository repo=new FileBasedStateRepository(file);
  FeatureState state1=repo.getFeatureState(MyFeature.FEATURE1);
  assertEquals(true,state1.isEnabled());
  assertEquals(Arrays.asList("chkal","tester"),state1.getUsers());
  FeatureState state2=repo.getFeatureState(MyFeature.FEATURE2);
  assertEquals(false,state2.isEnabled());
  assertEquals(Collections.emptyList(),state2.getUsers());
  FeatureState state3=repo.getFeatureState(MyFeature.FEATURE3);
  assertEquals(true,state3.isEnabled());
  assertEquals(Collections.emptyList(),state3.getUsers());
  FeatureState state4=repo.getFeatureState(MyFeature.FEATURE4);
  assertNull(state4);
}
