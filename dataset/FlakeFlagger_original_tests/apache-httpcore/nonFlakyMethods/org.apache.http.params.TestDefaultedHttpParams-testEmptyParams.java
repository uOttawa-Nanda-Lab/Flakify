@Test public void testEmptyParams(){
  DefaultedHttpParams deflt=new DefaultedHttpParams(new BasicHttpParams(),new BasicHttpParams());
  Assert.assertNull("The parameter should not be present",deflt.getParameter("param1"));
  Assert.assertFalse("The parameter should not be present",deflt.removeParameter("param1"));
  Assert.assertEquals(0,deflt.getNames().size());
  Assert.assertEquals(0,deflt.getLocalNames().size());
  Assert.assertEquals(0,deflt.getDefaultNames().size());
}
