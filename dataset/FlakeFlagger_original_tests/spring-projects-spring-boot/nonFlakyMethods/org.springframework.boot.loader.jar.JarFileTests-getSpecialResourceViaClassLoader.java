@Test public void getSpecialResourceViaClassLoader() throws Exception {
  URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{this.jarFile.getUrl()});
  assertThat(urlClassLoader.getResource("special/\u00EB.dat"),notNullValue());
  urlClassLoader.close();
}
