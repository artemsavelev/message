Project-test with use BD ClickHouse
=====================================


## Used technologies
* Database [ClickHouse](https://clickhouse.tech/docs/en/)
* Universal framework [Spring-boot](https://spring.io/projects/spring-boot)
* Communication protocol [WebSocket](https://spring.io/guides/gs/messaging-stomp-websocket/)
* Project Lombok [Lombok](https://projectlombok.org/)

## Database creation
```sql
create table if not exists message
(
	id UUID,
	message Nullable(String),
	created_date DateTime
)
engine = MergeTree PARTITION BY toYYYYMMDD(created_date) ORDER BY created_date;
```

## Interface

* Git [message-front](https://github.com/artemsavelev/message-front)

