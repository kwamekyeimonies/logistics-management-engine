FROM ubuntu:latest
LABEL authors="danieltenkorang"

ENTRYPOINT ["top", "-b"]