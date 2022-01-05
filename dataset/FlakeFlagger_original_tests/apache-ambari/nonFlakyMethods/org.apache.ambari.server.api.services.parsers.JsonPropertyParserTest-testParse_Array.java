@Test public void testParse_Array(){
  RequestBodyParser parser=new JsonPropertyParser();
  Set<Map<String,Object>> setProps=parser.parse(clustersJson);
  assertEquals(3,setProps.size());
  boolean cluster1Matches=false;
  boolean cluster2Matches=false;
  boolean cluster3Matches=false;
  Map<String,String> mapCluster1=new HashMap<String,String>();
  mapCluster1.put(PropertyHelper.getPropertyId("Clusters","cluster_name"),"unitTestCluster1");
  Map<String,String> mapCluster2=new HashMap<String,String>();
  mapCluster2.put(PropertyHelper.getPropertyId("Clusters","cluster_name"),"unitTestCluster2");
  mapCluster2.put(PropertyHelper.getPropertyId("Clusters","property1"),"prop1Value");
  Map<String,String> mapCluster3=new HashMap<String,String>();
  mapCluster3.put(PropertyHelper.getPropertyId("Clusters","cluster_name"),"unitTestCluster3");
  mapCluster3.put(PropertyHelper.getPropertyId("Clusters.Category","property2"),"prop2Value");
  for (  Map<String,Object> mapProps : setProps) {
    if (mapProps.equals(mapCluster1)) {
      cluster1Matches=true;
    }
 else     if (mapProps.equals(mapCluster2)) {
      cluster2Matches=true;
    }
 else     if (mapProps.equals(mapCluster3)) {
      cluster3Matches=true;
    }
  }
  assertTrue(cluster1Matches);
  assertTrue(cluster2Matches);
  assertTrue(cluster3Matches);
}
