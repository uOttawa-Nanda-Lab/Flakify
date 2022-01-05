@Test public void repositoryAuthentication(){
  doWithCustomUserHome(new Runnable(){
    @SuppressWarnings("unchecked") @Override public void run(){
      AetherGrapeEngine grapeEngine=createGrapeEngine();
      List<RemoteRepository> repositories=(List<RemoteRepository>)ReflectionTestUtils.getField(grapeEngine,"repositories");
      assertEquals(1,repositories.size());
      Authentication authentication=repositories.get(0).getAuthentication();
      assertNotNull(authentication);
    }
  }
);
}
