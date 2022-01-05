@Test public void testOwner() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setOwner("owner");
  assertThat(application.getOwner(),equalTo("owner"));
}
