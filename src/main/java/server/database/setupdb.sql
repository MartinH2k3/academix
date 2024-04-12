BEGIN;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Drop existing tables
DROP TABLE IF EXISTS "faculty_representatives" CASCADE;
DROP TABLE IF EXISTS "faculties" CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "admins" CASCADE;
DROP TABLE IF EXISTS "helpline_question" CASCADE;
DROP TABLE IF EXISTS "helpline_answer" CASCADE;
DROP TABLE IF EXISTS "students" CASCADE;
DROP TABLE IF EXISTS "quiz_results" CASCADE;
DROP TABLE IF EXISTS "school_results" CASCADE;
DROP TABLE IF EXISTS "universities" CASCADE;

-- Create tables
CREATE TABLE "universities" (
    "university_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(100) NOT NULL,
    "website_uri" VARCHAR(500)
);

CREATE TABLE "faculties" (
    "faculty_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "parent_university_id" UUID REFERENCES "universities"("university_id"),
    "name" VARCHAR(100) NOT NULL,
    "description" TEXT,
    "attribute1" INT DEFAULT 0,
    "attribute2" INT DEFAULT 0,
    "attribute3" INT DEFAULT 0,
    "website_uri" VARCHAR(500),
    "title_image_uri" VARCHAR(500)
);

CREATE TABLE "users" (
    "user_id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "username" VARCHAR(20) NOT NULL UNIQUE,
    "password_hash" CHAR(64) NOT NULL,
    "email" VARCHAR(320) UNIQUE,
    "type" VARCHAR(20) NOT NULL
);

CREATE TABLE "admins" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id")
);

CREATE TABLE "faculty_representatives" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id"),
    "first_name" VARCHAR(40),
    "last_name" VARCHAR(40),
    "verified" BOOLEAN,
    "faculty_id" UUID REFERENCES "faculties"("faculty_id")
);

CREATE TABLE "students" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "user_id" UUID REFERENCES "users"("user_id"),
    "first_name" VARCHAR(40),
    "last_name" VARCHAR(40),
    "phone_number" VARCHAR(15)
);

CREATE TABLE "helpline_question" (
    "question_id" BIGSERIAL PRIMARY KEY,
    "asked_by" UUID REFERENCES "users"("user_id"),
    "text" VARCHAR(500) NOT NULL,
    "type" VARCHAR(30) NOT NULL,
    "status" VARCHAR(30) NOT NULL
);

CREATE TABLE "helpline_answer" (
    "answer_id" BIGSERIAL PRIMARY KEY,
    "question_id" BIGINT REFERENCES "helpline_question"("question_id"),
    "responded_by" UUID REFERENCES "users"("user_id"),
    "response" VARCHAR(500) NOT NULL
);

CREATE TABLE "quiz_results" (
    "test_id" BIGSERIAL PRIMARY KEY,
    "user_id" UUID NOT NULL REFERENCES "users"("user_id"),
    "attribute1" INT DEFAULT 0,
    "attribute2" INT DEFAULT 0,
    "attribute3" INT DEFAULT 0
);

CREATE TABLE "school_results" (
    "school_result_id" BIGSERIAL PRIMARY KEY,
    "user_id" UUID NOT NULL REFERENCES "users"("user_id"),
    "attribute1" INT DEFAULT 0,
    "attribute2" INT DEFAULT 0,
    "attribute3" INT DEFAULT 0
);

-- Commit transaction
COMMIT;
