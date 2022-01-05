@Test public void customOrder(){
  this.healthAggregator.setStatusOrder(Status.UNKNOWN,Status.UP,Status.OUT_OF_SERVICE,Status.DOWN);
  Map<String,Health> healths=new HashMap<String,Health>();
  healths.put("h1",new Health.Builder().status(Status.DOWN).build());
  healths.put("h2",new Health.Builder().status(Status.UP).build());
  healths.put("h3",new Health.Builder().status(Status.UNKNOWN).build());
  healths.put("h4",new Health.Builder().status(Status.OUT_OF_SERVICE).build());
  assertEquals(Status.UNKNOWN,this.healthAggregator.aggregate(healths).getStatus());
}
