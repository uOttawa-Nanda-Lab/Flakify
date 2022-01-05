@Test public void testAddRemoveParam(){
  DefaultedHttpParams deflt=new DefaultedHttpParams(new BasicHttpParams(),new BasicHttpParams());
  Assert.assertFalse("The parameter should not be removed successfully",deflt.removeParameter("param1"));
  deflt.setParameter("param1","paramValue1");
  Assert.assertEquals(0,deflt.getDefaultNames().size());
  Assert.assertEquals(1,deflt.getNames().size());
  Assert.assertEquals(1,deflt.getLocalNames().size());
  Assert.assertTrue("The parameter should be removed successfully",deflt.removeParameter("param1"));
  Assert.assertFalse("The parameter should not be present",deflt.removeParameter("param1"));
  Assert.assertEquals(0,deflt.getDefaultNames().size());
  Assert.assertEquals(0,deflt.getNames().size());
  Assert.assertEquals(0,deflt.getLocalNames().size());
}
