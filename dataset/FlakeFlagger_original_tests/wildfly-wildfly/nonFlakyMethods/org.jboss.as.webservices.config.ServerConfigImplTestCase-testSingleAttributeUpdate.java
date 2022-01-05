@Test public void testSingleAttributeUpdate() throws Exception {
  internalTestSingleAttributeUpdate(new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setModifySOAPAddress(true);
    }
  }
);
  internalTestSingleAttributeUpdate(new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServiceHost("foo");
    }
  }
);
  internalTestSingleAttributeUpdate(new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServicePort(976);
    }
  }
);
  internalTestSingleAttributeUpdate(new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServiceSecurePort(5435);
    }
  }
);
  internalTestSingleAttributeUpdate(new Callback(){
    @Override public void setAttribute(    ServerConfig sc) throws Exception {
      sc.setWebServicePathRewriteRule("MY/TEST/PATH");
    }
  }
);
}
