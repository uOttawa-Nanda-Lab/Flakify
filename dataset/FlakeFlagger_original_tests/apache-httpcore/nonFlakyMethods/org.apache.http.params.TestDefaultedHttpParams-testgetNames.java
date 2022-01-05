@Test public void testgetNames(){
  DefaultedHttpParams params=new DefaultedHttpParams(addParams("local"),addParams("default"));
  Set<String> nameSet=params.getNames();
  Assert.assertEquals(3,nameSet.size());
  Set<String> localnameSet=params.getLocalNames();
  Assert.assertEquals(2,localnameSet.size());
  Set<String> defaultnameSet=params.getDefaultNames();
  Assert.assertEquals(2,defaultnameSet.size());
  params.setParameter("new",null);
  Assert.assertEquals(3,nameSet.size());
  Assert.assertEquals(2,localnameSet.size());
  Assert.assertEquals(2,defaultnameSet.size());
  nameSet=params.getNames();
  localnameSet=params.getLocalNames();
  defaultnameSet=params.getDefaultNames();
  Assert.assertEquals(4,nameSet.size());
  Assert.assertEquals(3,localnameSet.size());
  Assert.assertEquals(2,defaultnameSet.size());
}
