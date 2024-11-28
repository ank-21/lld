package main.java.com.phonepeTest;

import main.java.com.phonepeTest.enums.IssueType;
import main.java.com.phonepeTest.models.CustomerServiceAgent;
import main.java.com.phonepeTest.models.FilteredCriteria;
import main.java.com.phonepeTest.models.Issue;
import main.java.com.phonepeTest.service.AgentManager;
import main.java.com.phonepeTest.service.IssueManager;
import main.java.com.phonepeTest.strategies.RoundRobinAssignment;
import main.java.com.phonepeTest.utils.exception.AgentNotFoundException;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws AgentNotFoundException {
        AgentManager agentManager = new AgentManager();
        // Onboard a new agent
        agentManager.onboardAgent(new CustomerServiceAgent("1", "Ram", Set.of(IssueType.PAYMENT, IssueType.GOLD)));
        agentManager.onboardAgent(new CustomerServiceAgent("2", "Shyam", Set.of(IssueType.MUTUAL_FUND)));

        IssueManager issueManager = new IssueManager(agentManager, new RoundRobinAssignment());

        // Raising issues
        Issue issue1 = issueManager.raiseIssue(IssueType.GOLD, "Gold bought data not matching", "Gold Purchase Issue", "T1", "customer1@example.com", "cust001");
        Issue issue2 = issueManager.raiseIssue(IssueType.INSURANCE, "My insurance purchased but not shpowing in the dashboard", "Insurance not displaying", "T2", "customer2@example.com", "cust002");
        Issue issue3 = issueManager.raiseIssue(IssueType.PAYMENT, "My payment failed but money is debited", "PaymentFailure", "T3", "customer3@example.com", "cust003");

        // Generalized issue search
        FilteredCriteria filteredCriteria = new FilteredCriteria();

        filteredCriteria.setCustomerEmail("customer2@example.com");
        filteredCriteria.setIssueType(IssueType.INSURANCE);
        System.out.println("Filtered Issues: " + issueManager.getIssues(filteredCriteria));

        // View agent's assigned issues
        System.out.println("Agent Issues: " + agentManager.viewAgentIssues("1"));

        // Resolve an issue
        agentManager.getAllAgents().getFirst().markIssueResolved(issue1.getId());

        // Onboard a new agent
        agentManager.onboardAgent(new CustomerServiceAgent("3", "Charlie", Set.of(IssueType.GOLD)));

        // View agent work history
        agentManager.viewAgentWorkHistory("1");
    }
}
