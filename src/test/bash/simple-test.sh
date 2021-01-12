#!/bin/bash

# IMPORTS
source "/home/fab/Desktop/feirden-output-color-lib.sh"

echo "#         STARTING SCRIPTS        #"
# URLS:
# book URLS
#"http://localhost:8080/library/book?id="
#"http://localhost:8080/library/book?name-book="
#"http://localhost:8080/library/book/category?book-category="
#"http://localhost:8080/library/book/category?book-category="
#"http://localhost:8080/library/book/author?name-author="
#"http://localhost:8080/library/book/author?author-id="
#"http://localhost:8080/library/book/add-book"
# stock URL
#"http://localhost:8080/library/book/stock/book-id?id="
#"http://localhost:8080/library/stock/update"
# topic URLS
#"http://localhost:8080/library/topic/all"
#"http://localhost:8080/library/topic?name="
#"http://localhost:8080/library/topic/add-topic"
# publisher URLS
#"http://localhost:8080/library/publisher?id="
#"http://localhost:8080/library/publisher/all"
#"http://localhost:8080/library/publisher/add-publisher"
#"http://localhost:8080/library/publisher/update"
# author URLS
#"http://localhost:8080/library/publisher/author?id="
#"http://localhost:8080/library/publisher/author?name="
#"http://localhost:8080/library/publisher/add-author"


UrlsOfMethods=(
"http://localhost:8080/library/book?id="
"http://localhost:8080/library/book?name-book="
"http://localhost:8080/library/book/category?book-category="
"http://localhost:8080/library/book/category?book-category="
"http://localhost:8080/library/book/author?name-author="
"http://localhost:8080/library/book/author?author-id="
"http://localhost:8080/library/book/add-book"
# stock URL
"http://localhost:8080/library/book/stock/book-id?id="
"http://localhost:8080/library/stock/update"
# topic URLS
"http://localhost:8080/library/topic/all"
"http://localhost:8080/library/topic?name="
"http://localhost:8080/library/topic/add-topic"
# publisher URLS
"http://localhost:8080/library/publisher?id="
"http://localhost:8080/library/publisher/all"
"http://localhost:8080/library/publisher/add-publisher"
"http://localhost:8080/library/publisher/update"
# author URLS
"http://localhost:8080/library/publisher/author?id="
"http://localhost:8080/library/publisher/author?name="
"http://localhost:8080/library/publisher/add-author"
)
COUNTER=1;

for (( c=0; c<=18; c++ ))
    do 
        echo -e "${YELLOW} ${c} - URL: ${UrlsOfMethods[c]} ${NC}"
        echo ""
    done

echo "STARTING CURL's execution"
# curl --header "Content-Type: application/json" 'http://localhost:8080/library/add-publisher' --request POST --data '{"username":"jonh doe"}'
# CURL to get and send id as query param
# curl --header "Content-Type: application/json" -X GET 'http://localhost:8080/library/publisher?id=1'

echo "All topics"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[9]}"
echo ""

echo "All publishers"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[13]}"
echo ""

echo "All authors"

echo "Get topic by name"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[10]}Drama"
echo ""

echo "Get PUBLISHER by ID"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[12]}1"
echo ""

echo "Get Author by ID"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[16]}1"
echo ""

echo "Get Author by NAME"
curl --header "Content-Type: application/json" -X GET "${UrlsOfMethods[17]}Albert"
echo ""

