MIGRATION_USERNAME=portfoliodeveloper
MIGRATION_PASSWORD=portfoliodeveloper
DATABASE_URL=jdbc:postgresql://localhost:5432/portfoliodeveloper

db-clean:
	 ./mvnw flyway:clean -Dflyway.cleanDisabled=false -Dflyway.user=$(MIGRATION_USERNAME) -Dflyway.url=$(DATABASE_URL) -Dflyway.password=$(MIGRATION_PASSWORD)

# Run migration for development environment
db-migrate:
	 ./mvnw flyway:migrate -Dflyway.user=$(MIGRATION_USERNAME) -Dflyway.url=$(DATABASE_URL) -Dflyway.password=$(MIGRATION_PASSWORD)

# Run validate for development environment
db-validate:
	 ./mvnw flyway:validate -Dflyway.user=$(MIGRATION_USERNAME) -Dflyway.url=$(DATABASE_URL) -Dflyway.password=$(MIGRATION_PASSWORD)

# create the containers for development environment
app-up:
	docker-compose -p seniority-meter-environment up -d

# remove the containers
app-down:
	docker-compose -p seniority-meter-environment down