@Test public void multipleEntityManagerFactoriesDoNotOverwriteEachOther(){
  EntityManagerFactoryBuilder factory=new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),this.properties,null);
  LocalContainerEntityManagerFactoryBean result1=factory.dataSource(this.dataSource1).properties(Collections.singletonMap("foo","spam")).build();
  assertFalse(result1.getJpaPropertyMap().isEmpty());
  LocalContainerEntityManagerFactoryBean result2=factory.dataSource(this.dataSource2).build();
  assertTrue(result2.getJpaPropertyMap().isEmpty());
}
