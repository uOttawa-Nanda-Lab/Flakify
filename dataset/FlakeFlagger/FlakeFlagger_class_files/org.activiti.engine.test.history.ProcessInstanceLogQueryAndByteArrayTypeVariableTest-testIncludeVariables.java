/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 */
package org.activiti.engine.test.history;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricData;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;

/**

 */
public class ProcessInstanceLogQueryAndByteArrayTypeVariableTest extends PluggableActivitiTestCase {
	
	protected String processInstanceId;
	
	private static String LARGE_STRING_VALUE;
	
	static {
		StringBuilder sb = new StringBuilder("a");
		for(int i = 0; i < 4001; i++) {
		     sb.append("a");
		}
		LARGE_STRING_VALUE = sb.toString();
	}
	
	public void testIncludeVariables() {
		if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
			ProcessInstanceHistoryLog log = historyService.createProcessInstanceHistoryLogQuery(processInstanceId)
				.includeVariables()
				.singleResult();
			List<HistoricData> events = log.getHistoricData();
			assertEquals(1, events.size());
			
			for (HistoricData event : events) {
				assertTrue(event instanceof HistoricVariableInstance);
				assertEquals(((HistoricVariableInstanceEntity) event).getValue(), LARGE_STRING_VALUE);
			}
		}
	}
}