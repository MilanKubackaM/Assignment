package sk.stuba.fei.uim.vsa.pr1;
import javax.persistence.*;

@Entity
public class Student {
    @Id
    private Long aisId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private int study_year;
    private int study_semester;
    private String study_program;
    private Long final_thesis;
    public void setAisId(Long aisId) {
        this.aisId = aisId;
    }
    public Long getAisId() {
        return aisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudy_year() {
        return study_year;
    }

    public void setStudy_year(int study_year) {
        this.study_year = study_year;
    }

    public int getStudy_semester() {
        return study_semester;
    }

    public void setStudy_semester(int study_semester) {
        this.study_semester = study_semester;
    }

    public String getStudy_program() {
        return study_program;
    }

    public void setStudy_program(String study_program) {
        this.study_program = study_program;
    }

    public Long getFinal_thesis() {
        return final_thesis;
    }

    public void setFinal_thesis(Long final_thesis) {
        this.final_thesis = final_thesis;
    }
}
