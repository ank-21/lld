package main.java.com.phonepeTest.service;

import main.java.com.phonepeTest.models.CustomerServiceAgent;
import main.java.com.phonepeTest.models.Issue;
import main.java.com.phonepeTest.utils.exception.AgentNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AgentManager {
    private final List<CustomerServiceAgent> agents;

    public AgentManager() {
        this.agents = new ArrayList<>();
    }

    public void onboardAgent(CustomerServiceAgent agent) {
        agents.add(agent);
        System.out.println("Onboarded new agent: " + agent.getName());
    }

    public void offboardAgent(String agentId) {
        agents.removeIf(agent -> agent.getId().equals(agentId));
    }

    public List<CustomerServiceAgent> getAllAgents() {
        return agents;
    }

    public void viewAgentWorkHistory(String agentId) throws AgentNotFoundException {
        for (CustomerServiceAgent agent : agents) {
            if (agent.getId().equals(agentId)) {
                System.out.println("Work history for agent " + agent.getName() + ":");
                agent.getAssignedIssues().forEach(System.out::println);
                return;
            }
        }
        throw new AgentNotFoundException("No such agent found");
    }

    public List<Issue> viewAgentIssues(String agentId) throws AgentNotFoundException {
        for (CustomerServiceAgent agent : agents) {
            if (agent.getId().equals(agentId)) {
                return agent.getAssignedIssues();
            }
        }
        throw new AgentNotFoundException("No such agent found");
    }
}
