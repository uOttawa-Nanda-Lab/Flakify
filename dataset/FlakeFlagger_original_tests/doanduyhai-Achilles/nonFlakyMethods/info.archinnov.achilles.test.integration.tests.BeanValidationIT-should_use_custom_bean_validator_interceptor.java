@Test public void should_use_custom_bean_validator_interceptor() throws Exception {
  Long id=RandomUtils.nextLong();
  boolean exceptionRaised=false;
  Session nativeSession=this.manager.getNativeSession();
  Cluster cluster=nativeSession.getCluster();
  CustomValidationInterceptor interceptor=new CustomValidationInterceptor();
  Map<ConfigurationParameters,Object> configMap=new HashMap<>();
  configMap.put(NATIVE_SESSION,nativeSession);
  configMap.put(ENTITIES_LIST,Arrays.asList(EntityWithGroupConstraint.class));
  configMap.put(BEAN_VALIDATION_ENABLE,true);
  configMap.put(EVENT_INTERCEPTORS,Arrays.asList(interceptor));
  configMap.put(KEYSPACE_NAME,"achilles_test");
  PersistenceManagerFactory managerFactory=PersistenceManagerFactoryBuilder.build(cluster,configMap);
  PersistenceManager manager=managerFactory.createPersistenceManager();
  EntityWithGroupConstraint entity=new EntityWithGroupConstraint();
  entity.setId(id);
  StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");
  errorMessage.append("\tproperty 'name' of class '");
  errorMessage.append(EntityWithGroupConstraint.class.getCanonicalName()).append("'");
  try {
    manager.persist(entity);
  }
 catch (  AchillesBeanValidationException ex) {
    assertThat(ex.getMessage()).contains(errorMessage.toString());
    exceptionRaised=true;
  }
  assertThat(exceptionRaised).isTrue();
}
