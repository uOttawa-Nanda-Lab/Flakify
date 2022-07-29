package org.activiti.engine.test.api.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

public class ProcessDefinitionQueryByLatestTest extends PluggableActivitiTestCase {

	private static String XML_FILE_PATH = "org/activiti/engine/test/repository/latest/";
	
	  protected List<String> deploy(List<String> xmlFileNameList) throws Exception {
		List<String> deploymentIdList = new ArrayList<String>();
		for(String xmlFileName : xmlFileNameList){
		    String deploymentId = repositoryService
		  	      .createDeployment()
		  	      .name(XML_FILE_PATH + xmlFileName)
		  	      .addClasspathResource(XML_FILE_PATH + xmlFileName)
		  	      .deploy()
		  	      .getId();
		    deploymentIdList.add(deploymentId);
		}
		return deploymentIdList;
	}

	private void unDeploy(List<String> deploymentIdList) throws Exception {
		for(String deploymentId : deploymentIdList){
			repositoryService.deleteDeployment(deploymentId, true);
		}
	}

	public void testQueryByLatestAndDeploymentId() throws Exception{List<String> xmlFileNameList=Arrays.asList("name_testProcess1_one.bpmn20.xml","name_testProcess1_two.bpmn20.xml","name_testProcess2_one.bpmn20.xml");List<String> deploymentIdList=deploy(xmlFileNameList);ProcessDefinitionQuery deploymentQuery1=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdList.get(0)).latestVersion();List<ProcessDefinition> processDefinitions=deploymentQuery1.list();assertEquals(0,processDefinitions.size());ProcessDefinitionQuery deploymentQuery2=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdList.get(1)).latestVersion();processDefinitions=deploymentQuery2.list();assertEquals(1,processDefinitions.size());assertEquals("testProcess1",processDefinitions.get(0).getKey());unDeploy(deploymentIdList);}
}
