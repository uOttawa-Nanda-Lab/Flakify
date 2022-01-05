@Test public void test() throws Exception {
  InputStream is=getXmlUrl("jbossws-deployment-aspects-example.xml").openStream();
  try {
    List<DeploymentAspect> das=WSDeploymentAspectParser.parse(is,this.getClass().getClassLoader());
    assertEquals(4,das.size());
    boolean da1Found=false;
    boolean da2Found=false;
    boolean da3Found=false;
    boolean da4Found=false;
    for (    DeploymentAspect da : das) {
      if (da instanceof TestDA2) {
        da2Found=true;
        TestDA2 da2=(TestDA2)da;
        assertEquals("myString",da2.getTwo());
      }
 else       if (da instanceof TestDA3) {
        da3Found=true;
        TestDA3 da3=(TestDA3)da;
        assertNotNull(da3.getList());
        assertTrue(da3.getList().contains("One"));
        assertTrue(da3.getList().contains("Two"));
      }
 else       if (da instanceof TestDA4) {
        da4Found=true;
        TestDA4 da4=(TestDA4)da;
        assertEquals(true,da4.isBool());
        assertNotNull(da4.getMap());
        assertEquals(1,(int)da4.getMap().get("One"));
        assertEquals(3,(int)da4.getMap().get("Three"));
      }
 else       if (da instanceof TestDA1) {
        da1Found=true;
      }
    }
    assertTrue(da1Found);
    assertTrue(da2Found);
    assertTrue(da3Found);
    assertTrue(da4Found);
  }
  finally {
    if (is != null) {
      is.close();
    }
  }
}
