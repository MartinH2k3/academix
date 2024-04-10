BEGIN;

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

-- Create ENUM types, cause that's the Postgres way
CREATE TYPE user_type AS ENUM ('admin', 'student', 'faculty representative');
CREATE TYPE question_type AS ENUM ('accessrequest', 'bugfix', 'general');
CREATE TYPE question_status AS ENUM ('answered', 'unanswered');

-- Create tables
CREATE TABLE "universities" (
    "university_id" UUID PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "website_uri" VARCHAR(500)
);

CREATE TABLE "faculties" (
    "faculty_id" UUID PRIMARY KEY,
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
    "user_id" UUID PRIMARY KEY,
    "username" VARCHAR(20) NOT NULL UNIQUE,
    "email" VARCHAR(320) UNIQUE,
    "password_hash" CHAR(20) NOT NULL,
    "type" user_type NOT NULL
);

CREATE TABLE "admins" (
    "user_id" UUID PRIMARY KEY REFERENCES "users"("user_id")
);

CREATE TABLE "faculty_representatives" (
    "user_id" UUID PRIMARY KEY REFERENCES "users"("user_id"),
    "first_name" VARCHAR(40),
    "last_name" VARCHAR(40),
    "verified" BOOLEAN,
    "faculty_id" UUID REFERENCES "faculties"("faculty_id")
);

CREATE TABLE "students" (
    "user_id" UUID PRIMARY KEY REFERENCES "users"("user_id"),
    "first_name" VARCHAR(40),
    "last_name" VARCHAR(40),
    "phone_number" VARCHAR(15)
);

CREATE TABLE "helpline_question" (
    "question_id" UUID PRIMARY KEY,
    "asked_by" UUID REFERENCES "users"("user_id"),
    "text" VARCHAR(500) NOT NULL,
    "type" question_type NOT NULL,
    "status" question_status NOT NULL
);

CREATE TABLE "helpline_answer" (
    "question_id" UUID PRIMARY KEY REFERENCES "helpline_question"("question_id"),
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
