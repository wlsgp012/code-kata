package dojo.inaction.ch11.invoke;

import java.util.List;
import java.util.function.Predicate;

public class IssueJ {

  static class JavaIssuePredicate implements Predicate<Issue> {
    private String project;
    public JavaIssuePredicate(String project) {
      this.project = project;
    }

    @Override
    public boolean test(Issue issue) {
      return project.equals(issue.getProject()) && isImportant(issue);
    }

    private boolean isImportant(Issue issue) {
      String priority = issue.getPriority();
      return "Bug".equals(issue.getType()) && ("Major".equals(priority) || "Critical".equals(priority));
    }
  }

  public static void main(String[] args) {
    Issue issue1 = new Issue("a", "IDEA", "Bug", "Major");
    Issue issue2 = new Issue("b", "Kotlin", "Bug", "Major");
    JavaIssuePredicate predicate = new JavaIssuePredicate("IDEA");
    List.of(issue1, issue2).stream().filter(predicate).forEach(System.out::println);
  }

}
