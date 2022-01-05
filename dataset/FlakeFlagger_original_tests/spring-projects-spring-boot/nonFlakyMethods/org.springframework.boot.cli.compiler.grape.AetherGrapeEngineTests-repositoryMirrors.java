@Test public void repositoryMirrors(){
  doWithCustomUserHome(new Runnable(){
    @SuppressWarnings("unchecked") @Override public void run(){
      AetherGrapeEngine grapeEngine=createGrapeEngine();
      List<RemoteRepository> repositories=(List<RemoteRepository>)ReflectionTestUtils.getField(grapeEngine,"repositories");
      assertEquals(1,repositories.size());
      assertEquals("central-mirror",repositories.get(0).getId());
    }
  }
);
}
