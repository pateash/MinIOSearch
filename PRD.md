# Product Requirement Document (PRD)

## Purpose
The purpose of this document is to outline the requirements for a search service application capable of searching content within documents stored in MinIO.

## Objectives

1. Implement the solution capable of searching document content.
2. Provide a demonstration of the application.
3. Optional: Write a PRD for the problem statement.

## Functional Requirements

1. **Connection to MinIO**: Establish a real-time connection with MinIO to access recently added data.

2. **Document Formats**: Handle `.txt` and `.log` files for the demonstration. Optionally support `.pdf` and `.docx` files, extracting text content with Apache Tika.

3. **Indexing and Search**: Index the content of the files to enable full-text search capabilities, potentially using Elasticsearch.

4. **Search API**: Provide an API that accepts a search term/token and returns a list of files and paths in MinIO, e.g., `curl https://<search-service-host>/search?q=”@gmail.com”`.

## Non-Functional Requirements

1. **Technology Stack**: Freedom to choose any tool/framework.
2. **Code Quality**: Solution should reflect high-quality code standards and working functionality.
3. **Documentation**: Code should be well documented and understandable.
4. **Setup**: Reference a provided docker-compose.yaml file for test environment setup.

## Out of Scope

- Detailed UI design for the search service.
- Authentication and authorization for API access.

## Considerations

- The solution should reflect the ability to design and code with high standards.
- Code samples from the web can be used but should be explained.
- Reach out via email for clarifications.

---

For more details on the MinIO product overview, visit [MinIO Overview](https://min.io/product/overview).
