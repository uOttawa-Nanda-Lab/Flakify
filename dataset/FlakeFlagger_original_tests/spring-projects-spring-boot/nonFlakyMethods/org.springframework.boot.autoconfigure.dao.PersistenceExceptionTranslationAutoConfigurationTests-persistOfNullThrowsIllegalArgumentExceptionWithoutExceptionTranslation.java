@Test(expected=IllegalArgumentException.class) public void persistOfNullThrowsIllegalArgumentExceptionWithoutExceptionTranslation(){
  this.context=new AnnotationConfigApplicationContext(EmbeddedDataSourceConfiguration.class,HibernateJpaAutoConfiguration.class,TestConfiguration.class);
  this.context.getBean(TestRepository.class).doSomething();
}
