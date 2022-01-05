@Test public void entityManagerFactoryPropertiesNotOverwritingDefaults(){
  EntityManagerFactoryBuilder factory=new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),this.properties,null);
  LocalContainerEntityManagerFactoryBean result1=factory.dataSource(this.dataSource1).properties(Collections.singletonMap("foo","spam")).build();
  assertFalse(result1.getJpaPropertyMap().isEmpty());
  assertTrue(this.properties.getProperties().isEmpty());
}
