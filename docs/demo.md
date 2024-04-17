
## Demo workflow 
1. Show PRD
2. Show HLD
3. Start docker and run   `docker-compose down && docker-compose up -d`
1. Run `1_setup_rabbit.sh`
2. Run `2_setup_minio.sh`
1. Delete elastic search index
1. Delete all data from bucket 
   1. Upload file `hello.txt`
   2. search and show results
   3. Upload file `assignment.pdf`
   4. search and show results
1. Code deep dive
2. More enhancements ( ref - PRD )
