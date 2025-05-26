
# Etapa 1: Compilação
FROM openjdk:17 as builder
WORKDIR /app
COPY src/ ./src
COPY *.java .
# se tiver arquivos Java fora de src
RUN javac src/*.java -d out

# Etapa 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/out .
CMD ["java", "Main"]
# substitua "Main" pelo nome da sua classe principal
#docker-compose up --build (pra coinstruir e rodar tudo)
#docker exec -it nome_do_container_db mysql -uroot -proot (posso acessar o mysql pelo terminal)
