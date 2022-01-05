@Test public void testWebConfigurationWithExtraRole() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(EndpointAutoConfiguration.class,EndpointWebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,SecurityAutoConfiguration.class,ManagementSecurityAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  UserDetails user=getUser();
  assertTrue(user.getAuthorities().containsAll(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN")));
}
