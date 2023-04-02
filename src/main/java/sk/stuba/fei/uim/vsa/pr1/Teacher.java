package sk.stuba.fei.uim.vsa.pr1;
import javax.persistence.*;
@Entity
public class Teacher {
    @Id
    private Long aisId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private String institute;
    @Column(nullable = false)
    private String department;
    private String final_thesis;

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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFinal_thesis() {
        return final_thesis;
    }

    public void setFinal_thesis(String final_thesis) {
        this.final_thesis = final_thesis;
    }
}
