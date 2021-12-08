package dojo.inaction.ch11.invoke

data class Issue(
    val id: String,
    val project: String,
    val type: String,
    val priority: String,
)

class ImportantIssuesPredicate(val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean = issue.project == project && issue.isImportant()

    private fun Issue.isImportant(): Boolean =
        type == "Bug" && (priority == "Major" || priority == "Critical")
}

fun main() {
    val issue1 = Issue("a", "IDEA", "Bug", "Major")
    val issue2 = Issue("b", "Kotlin", "Bug", "Major")
    val predicate = ImportantIssuesPredicate("IDEA")
    listOf(issue1, issue2).filter(predicate).forEach { println(it) }
}

