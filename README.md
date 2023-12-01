# CA3

The link to the PDF CA brief [Redirects you to moodle](https://2324-moodle.dkit.ie/pluginfile.php/1184349/mod_resource/content/0/Web%20Patterns%20-%20CA3%20-%202023.pdf).

## Task Description:
Develop a web-based three tier library application that can support borrowing (via postal delivery) and
returning of books. The three tiers should be made up of:

1. Front end - JSPs and HTML pages
2. Middle tier – Servlet & required classes for functionality
3. Back end – MySQL Database OR JPA-supported Entities
   The project is a web application & should be developed fully. This includes a well-constructed backend
   database (taking security considerations into account) as well as validation on server-side AND client-side
   (Javascript or HTML5 validation included to check that the fields are filled in with appropriate data when a
   form is submitted) & some web design.
   Application Functionality
   The web application you are developing is a library site, which has great scope for functionality. You should
   develop all features in the best way possible, not just get them to function.
4. View books
5. Search for a book (or books)
6. Borrowing a book (or books)
7. Returning a book (or books)
8. Log in to the system
9. Register with the system
10. View my (the user’s) profile
11. Edit my (the user’s) details
12. View my (the user’s) current loans
13. View my (the user’s) previous loans
14. View my (the user’s) current overdue fees outstanding.
15. Pay overdue fees
16. Programmer-defined (Group member A)
17. Programmer-defined (Group member B)
18. Programmer-defined (Group member C)

## Programmer-Defined Functionality

Each developer/group-member is required to provide one extra function of their own design that they deem
useful to the library. Examples of this include providing extra security in some way, displaying information
from an external service, providing functionality as a RESTful service, tagging books, feedback on books,
searching by tag, filtering your displayed information by different criteria (e.g. genre), providing alternative
ordering (sorting) options etc. Each developer is responsible for all extra code required to provide their
feature, and the feature (and its developer) must be identified at the top of all classes as well as in a separate
text file.

## Applying Patterns

You must develop your code using the DAO (for database interaction), Front Controller, Command and
Factory patterns (working together to provide servlet functionality). Your site should only contain a single
controller (unless you have a strong justification for more) and servlet functionality should be provided in
Commands where appropriate.

## Testing

You are required to write Junit tests for all DAO code in your project (you do not have to test the DAO class
itself). These tests should be well-structured and confirm that all methods return the appropriate
information AND that the database has been changed (where applicable). They should test multiple cases,
not just the intended behaviour. You do not need to write isolation (mock) tests, just integration tests.

> [!NOTE]
> Generation of test classes without amending the tests to examine the logic of the methods will not
earn any marks.

## Documentation

You must provide a text file outlining the features of your website and stating the files in which the code for
these features lies. In the case of the “programmer-defined” features, you must also identify which of the
group members developed that feature. Without this, your code will not be marked.

You must also write Javadoc comments for all methods (including tests), interfaces and classes (excluding
DTOs & JSPs). These comments should include information on return types, parameters, method
functionality and the meaning of any potential exceptions that can be thrown (remember, this tag only
applies to methods that contain the throws keyword)

> [!NOTE]
> Generation of javadoc comments without including programmer-created information will not earn
any marks.

## Project management:

You are required to create and maintain source control via git and github. Code should be regularly
committed (with appropriate commit messages indicating what is being added/changed/removed/fixed etc)
and pushed to the shared repository by all team members.

## Marks breakdown:

* Core functionality – 60%
* Individual programmer-defined function – 10%
* Junit tests – 15%
* Javadocs – 10%
* Source control – 5%

The link to the PDF CA brief [Redirects you to moodle](https://2324-moodle.dkit.ie/pluginfile.php/1184349/mod_resource/content/0/Web%20Patterns%20-%20CA3%20-%202023.pdf).

### Submission instructions:

Upload a zipped copy of your Netbeans project, database SQL file/file to populate database with entities,
and features list text file (and where they can be found in the code) to Moodle, along with a link to your
github repository.

> [!NOTE]
> Only one upload per group.

> [!IMPORTANT]
> Submission Deadline: January 7th 23:59