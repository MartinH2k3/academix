package common.dto;

public class FacultyDTO {
    public String university_name;
    public String faculty_name;
    public String description;
    public String field;
    public String minimal_grade;
    public String website_url;
    public String title_image_url;

    public FacultyDTO() {
    }

    public FacultyDTO(String university_name, String faculty_name, String description, String field, String minimal_grade, String website_url, String title_image_url) {
        this.university_name = university_name;
        this.faculty_name = faculty_name;
        this.description = description;
        this.field = field;
        this.minimal_grade = minimal_grade;
        this.website_url = website_url;
        this.title_image_url = title_image_url;
    }
}
