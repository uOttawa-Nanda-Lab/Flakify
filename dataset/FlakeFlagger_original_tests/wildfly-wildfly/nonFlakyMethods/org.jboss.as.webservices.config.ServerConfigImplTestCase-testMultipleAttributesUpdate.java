@Test public void testMultipleAttributesUpdate() throws Exception {
  Callback cbA=new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setModifySOAPAddress(true);
    }
  }
;
  Callback cbB=new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServiceHost("foo");
    }
  }
;
  Callback cbC=new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServicePort(976);
    }
  }
;
  Callback cbD=new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServiceSecurePort(5435);
    }
  }
;
  Callback cbE=new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServicePathRewriteRule("MY/TEST/PATH");
    }
  }
;
  internalTestMultipleAttributeUpdate(cbA,new Callback[]{cbB,cbC,cbD,cbE});
  internalTestMultipleAttributeUpdate(cbB,new Callback[]{cbA,cbC,cbD,cbE});
  internalTestMultipleAttributeUpdate(cbC,new Callback[]{cbA,cbB,cbD,cbE});
  internalTestMultipleAttributeUpdate(cbD,new Callback[]{cbA,cbB,cbC,cbE});
  internalTestMultipleAttributeUpdate(cbE,new Callback[]{cbA,cbB,cbC,cbD});
}
