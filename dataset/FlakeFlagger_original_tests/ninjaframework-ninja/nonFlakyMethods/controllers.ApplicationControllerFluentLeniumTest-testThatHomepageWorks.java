@Test public void testThatHomepageWorks(){
  goTo(getServerAddress() + "/");
  System.out.println("title: " + title());
  assertTrue(title().contains("Home page"));
  click("#login");
  assertTrue(url().contains("login"));
}
