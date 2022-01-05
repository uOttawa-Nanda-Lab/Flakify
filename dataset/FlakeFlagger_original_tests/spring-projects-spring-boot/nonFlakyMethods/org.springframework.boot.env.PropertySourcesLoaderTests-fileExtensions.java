@Test public void fileExtensions(){
  assertTrue(this.loader.getAllFileExtensions().contains("yml"));
  assertTrue(this.loader.getAllFileExtensions().contains("yaml"));
  assertTrue(this.loader.getAllFileExtensions().contains("properties"));
}
