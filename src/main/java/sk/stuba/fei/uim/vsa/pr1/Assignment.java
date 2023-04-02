package sk.stuba.fei.uim.vsa.pr1;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Assignment {
    public Assignment() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reg_number;
    private String name;
    @Column(nullable = false)
    private String description;
    private String work_place;
    private LocalDate date_release;
    private LocalDate date_deadline;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne
    private Student student;

    public void setType(Type type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(Long reg_number) {
        this.reg_number = ("FEI-" + String.valueOf(reg_number));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork_place() {
        return work_place;
    }

    public void setWork_place(String work_place) {
        this.work_place = work_place;
    }

    public LocalDate getDate_release() {
        return date_release;
    }

    public void setDate_release(LocalDate date_release) {
        this.date_release = date_release;
    }

    public LocalDate getDate_deadline() {
        return this.date_deadline;
    }

    public void setDate_deadline(LocalDate date_deadline) {
        this.date_deadline = date_deadline;
    }
}
