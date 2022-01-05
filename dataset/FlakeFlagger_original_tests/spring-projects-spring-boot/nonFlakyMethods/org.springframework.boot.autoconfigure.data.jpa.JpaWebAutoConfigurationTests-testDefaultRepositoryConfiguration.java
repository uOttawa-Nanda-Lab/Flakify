@Test public void testDefaultRepositoryConfiguration() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,SpringDataWebAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(CityRepository.class));
  assertNotNull(this.context.getBean(PageableHandlerMethodArgumentResolver.class));
  assertTrue(this.context.getBean(FormattingConversionService.class).canConvert(Long.class,City.class));
}
