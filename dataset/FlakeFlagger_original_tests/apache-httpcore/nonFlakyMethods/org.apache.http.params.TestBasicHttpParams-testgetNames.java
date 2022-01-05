@Test public void testgetNames(){
  BasicHttpParams params=new BasicHttpParams();
  Set<String> nameSet=params.getNames();
  Assert.assertTrue(nameSet.isEmpty());
  params.setBooleanParameter("true",true);
  Assert.assertTrue(nameSet.isEmpty());
  nameSet=params.getNames();
  Assert.assertFalse(nameSet.isEmpty());
  Assert.assertEquals(1,nameSet.size());
  Iterator<String> iterator=nameSet.iterator();
  Assert.assertTrue("Iterator has an entry",iterator.hasNext());
  String entry=iterator.next();
  Assert.assertTrue(((Boolean)params.getParameter(entry)).booleanValue());
}
