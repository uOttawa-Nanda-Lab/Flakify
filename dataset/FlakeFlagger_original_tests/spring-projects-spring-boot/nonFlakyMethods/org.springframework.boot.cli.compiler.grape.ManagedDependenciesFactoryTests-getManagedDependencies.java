@Test public void getManagedDependencies(){
  List<Dependency> dependencyList=new ArrayList<Dependency>();
  dependencyList.add(new Dependency("g1","a1","1"));
  dependencyList.add(new Dependency("g1","a2","1"));
  ManagedDependencies dependencies=mock(ManagedDependencies.class);
  given(dependencies.iterator()).willReturn(dependencyList.iterator());
  ManagedDependenciesFactory factory=new ManagedDependenciesFactory(dependencies);
  List<org.eclipse.aether.graph.Dependency> result=factory.getManagedDependencies();
  assertThat(result.size(),equalTo(2));
  assertThat(result.get(0).toString(),equalTo("g1:a1:jar:1 (compile)"));
  assertThat(result.get(1).toString(),equalTo("g1:a2:jar:1 (compile)"));
}
