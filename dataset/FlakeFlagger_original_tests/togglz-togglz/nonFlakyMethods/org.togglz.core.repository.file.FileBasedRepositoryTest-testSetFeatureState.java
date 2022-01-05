@Test public void testSetFeatureState() throws IOException {
  FileBasedStateRepository repo=new FileBasedStateRepository(file);
  repo.setFeatureState(new FeatureState(MyFeature.FEATURE1,false));
  repo.setFeatureState(new FeatureState(MyFeature.FEATURE2,true,Arrays.asList("a","b","c")));
  FileInputStream fis=new FileInputStream(file);
  Properties p=new Properties();
  p.load(fis);
  assertEquals(4,p.size());
  assertEquals("false",p.getProperty("FEATURE1"));
  assertEquals("true",p.getProperty("FEATURE2"));
  assertEquals("a,b,c",p.getProperty("FEATURE2.users"));
  assertEquals("true",p.getProperty("FEATURE3"));
}
