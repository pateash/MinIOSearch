# Product Requirement Document (PRD)

## Purpose
The purpose of this document is to outline the requirements for a search service application capable of searching content within documents stored in MinIO.

## Objectives

1. Implement the solution capable of searching document content.
2. Provide a demonstration of the application.
3. Writing a PRD for the problem statement.

## Functional Requirements

1. **Connection to MinIO**: Establish a connection with MinIO to access added data.

2. **Document Formats**: Handle `.txt` and `.log` files for the demonstration. supporting `.pdf` and `.docx` files, extracting text content with Apache Tika.

3. **Indexing and Search**: Index the content of the files to enable full-text search capabilities, potentially using Elasticsearch.

4. **Search API**: Provide an API that accepts a search term/token and returns a list of files and paths in MinIO, e.g., 

5. **Real time processing**: Service should be designed to process files from MinIO promptly, ensuring near real-time processing.

6. **Manual upload from UI**: Service should also handle file uploads; Users can manually upload files to MinIO via the UI.

```bash
curl --location 'http://localhost:8080/api/v1/search?w=borneo' \
--header 'Authorization: Basic dXNlcjpzZWNyZXQ='
```

## Non-Functional Requirements

1. **Technology Stack**: Freedom to choose any tool/framework.
2. **Code Quality**: Solution should reflect high-quality code standards and working functionality.
3. **Documentation**: Code should be well documented and understandable.
4. **Setup**: Reference a provided docker-compose.yaml file for test environment setup.

## Out of Scope 
- Detailed UI design for the search service. ( Included )
- Basic Authentication and authorization for API access.( Included ) 
- Advanced token based authentication
- Low level design ( LLD )



