public void testQueryByOnlyMessages(){
  JobQuery query=managementService.createJobQuery().messages();
  verifyQueryResults(query,1);
}
