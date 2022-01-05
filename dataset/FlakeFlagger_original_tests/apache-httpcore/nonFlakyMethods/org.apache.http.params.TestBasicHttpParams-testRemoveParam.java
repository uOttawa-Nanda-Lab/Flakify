@Test public void testRemoveParam(){
  BasicHttpParams params=new BasicHttpParams();
  params.setParameter("param1","paramValue1");
  Assert.assertTrue("The parameter should be removed successfully",params.removeParameter("param1"));
  Assert.assertFalse("The parameter should not be present",params.removeParameter("param1"));
  params=new BasicHttpParams();
  Assert.assertFalse("The parameter should not be present",params.removeParameter("param1"));
}
