@Test public void testDefault() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setDefault(true);
  assertThat(application.isDefault(),is(true));
}
