package main.java.com.phonepeTest.strategies;

import main.java.com.phonepeTest.models.CustomerServiceAgent;
import main.java.com.phonepeTest.models.Issue;
import main.java.com.phonepeTest.utils.exception.AgentNotFoundException;

import java.util.List;

public interface AssignmentStrategy {
    CustomerServiceAgent assignAgent(List<CustomerServiceAgent> agents, Issue issue) throws AgentNotFoundException;
}
