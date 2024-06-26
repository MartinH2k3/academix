BEGIN;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Drop existing tables
DROP TABLE IF EXISTS "requests" CASCADE;
DROP TABLE IF EXISTS "faculty_representatives" CASCADE;
DROP TABLE IF EXISTS "faculties" CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "admins" CASCADE;
DROP TABLE IF EXISTS "helpline_questions" CASCADE;
DROP TABLE IF EXISTS "helpline_answers" CASCADE;
DROP TABLE IF EXISTS "students" CASCADE;
DROP TABLE IF EXISTS "universities" CASCADE;

-- Create tables
CREATE TABLE "universities" (
    "university_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE "faculties" (
    "faculty_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "parent_university_id" UUID REFERENCES "universities"("university_id"),
    "name" VARCHAR(100) NOT NULL,
    "description" TEXT,
    "field" VARCHAR(20) NOT NULL,
    "minimal_grade" DECIMAL(3, 2),
    "website_url" VARCHAR(500),
    "title_image_url" VARCHAR(500),
    UNIQUE ("parent_university_id", "name")
);

CREATE TABLE "users" (
    "user_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "username" VARCHAR(20) NOT NULL UNIQUE,
    "password_hash" CHAR(64) NOT NULL,
    "email" VARCHAR(320) UNIQUE,
    "first_name" VARCHAR(40),
    "last_name" VARCHAR(40),
    "phone_number" VARCHAR(15),
    "type" VARCHAR(30) NOT NULL
);

CREATE TABLE "admins" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id")
);

CREATE TABLE "faculty_representatives" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id"),
    "verified" BOOLEAN,
    "faculty_id" UUID REFERENCES "faculties"("faculty_id")
);

CREATE TABLE "students" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id")
);

CREATE TABLE "helpline_questions" (
    "question_id" BIGSERIAL PRIMARY KEY,
    "asked_by" UUID REFERENCES "users"("user_id") NOT NULL,
    "subject" VARCHAR(100) NOT NULL,
    "text" VARCHAR(500) NOT NULL,
    "status" VARCHAR(30) NOT NULL
);

CREATE TABLE "helpline_answers" (
    "answer_id" BIGSERIAL PRIMARY KEY,
    "question_id" BIGINT REFERENCES "helpline_questions"("question_id"),
    "response" VARCHAR(500) NOT NULL
);

CREATE TABLE "requests" (
    "request_id" BIGSERIAL PRIMARY KEY,
    "user_id" UUID REFERENCES "users"("user_id"),
    "status" VARCHAR(30) NOT NULL
);


-- Commit transaction
COMMIT;

