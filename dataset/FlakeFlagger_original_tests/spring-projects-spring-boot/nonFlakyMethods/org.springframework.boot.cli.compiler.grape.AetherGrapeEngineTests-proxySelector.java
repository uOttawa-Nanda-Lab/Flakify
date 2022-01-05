@Test public void proxySelector(){
  doWithCustomUserHome(new Runnable(){
    @Override public void run(){
      AetherGrapeEngine grapeEngine=createGrapeEngine();
      DefaultRepositorySystemSession session=(DefaultRepositorySystemSession)ReflectionTestUtils.getField(grapeEngine,"session");
      assertTrue(session.getProxySelector() instanceof CompositeProxySelector);
    }
  }
);
}
