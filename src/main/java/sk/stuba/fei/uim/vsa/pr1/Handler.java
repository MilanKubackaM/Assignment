package sk.stuba.fei.uim.vsa.pr1;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class Handler extends AbstractThesisService<Student, Teacher, Assignment>{

    @Override
    public Student createStudent(Long aisId, String name, String email) {
        if(aisId < 1 || name == null || email == null || name.trim().split("\\s+").length < 2)
            return null;
        if (!email.endsWith("@stuba.sk"))
            return null;

        EntityManager em = emf.createEntityManager();
        Student student = new Student();

        student.setAisId(aisId);
        student.setName(name);
        student.setEmail(email);
        em.getTransaction().begin();

        try{
            em.persist(student);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
        return student;
    }

    @Override
    public Student getStudent(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student;
        try{
            student = em.find(Student.class, id);
            if(student == null)
                return null;
            em.close();
            return student;

        }catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    @Override
    public Student updateStudent(Student student) {
        if (student == null || student.getAisId() == null) {
            throw new IllegalArgumentException();
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if(student.getAisId() < 1 || student.getName() == null || student.getEmail() == null || student.getName().trim().split("\\s+").length < 2)
            return null;
        if (!student.getEmail().endsWith("@stuba.sk"))
            return null;

        try {
            Student existingStudent = em.find(Student.class, student.getAisId());
            if (existingStudent != null) {
                if(student.getName() != null)
                    existingStudent.setName(student.getName());
                if(student.getEmail() != null)
                    existingStudent.setEmail(student.getEmail());
                if(student.getAisId() != null){
                    existingStudent.setAisId(student.getAisId());
                    if(student.getFinal_thesis() != null){
                        Assignment thesis = em.find(Assignment.class, student.getFinal_thesis());
                        thesis.setStudent(student);
                        //updateThesis(thesis);
                    }
                }
                if(student.getFinal_thesis() != null)
                    existingStudent.setFinal_thesis(student.getFinal_thesis());
                if(student.getStudy_program() != null)
                    existingStudent.setStudy_program(student.getStudy_program());
                if(student.getStudy_semester() != 0)
                    existingStudent.setStudy_semester(student.getStudy_semester());

                em.merge(existingStudent);
                em.getTransaction().commit();
            }
            em.close();
            return existingStudent;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }


    @Override
    public List<Student> getStudents() {
        EntityManager em = emf.createEntityManager();
        List<Student> students;

        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            if(query.getResultList() == null)
                return null;
            students = query.getResultList();
            return students;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Student deleteStudent(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
        if(getStudent(id) == null)
            return null;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student;

        try {
            student = em.find(Student.class, id);
            if(student == null)
                return null;
            if (student.getFinal_thesis() != null) {
                Assignment assignment = em.find(Assignment.class, getThesisByStudent(id).getId());
                assignment.setStatus(Status.FREE);
                assignment.setStudent(null);
            }
            em.remove(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            student = null;
        } finally {
            em.close();
        }
        return student;
    }


    @Override
    public Teacher createTeacher(Long aisId, String name, String email, String department) {
        if(aisId == null)
            throw new IllegalArgumentException();
        if(name == null || email == null || department == null || name.trim().split("\\s+").length < 2)
            return null;
        if (!email.endsWith("@stuba.sk"))
            return null;

        EntityManager em = emf.createEntityManager();
        Teacher teacher = new Teacher();
        teacher.setAisId(aisId);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setDepartment(department);
        em.getTransaction().begin();

        try{
            em.persist(teacher);
            em.getTransaction().commit();
            em.close();
            return teacher;

        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }

    @Override
    public Teacher getTeacher(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
            Teacher teacher = em.find(Teacher.class, id);
            em.close();
            return teacher;
        } catch (Exception e){
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        if (teacher == null || teacher.getAisId() == null) {
            throw new IllegalArgumentException();
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Teacher existingTeacher = em.find(Teacher.class, teacher.getAisId());
            if (existingTeacher != null) {
                if(teacher.getName() != null)
                    existingTeacher.setName(teacher.getName());
                if(teacher.getEmail() != null)
                    existingTeacher.setEmail(teacher.getEmail());
                if(teacher.getDepartment() != null)
                    existingTeacher.setDepartment(teacher.getDepartment());
                if(teacher.getAisId() != null)
                    existingTeacher.setAisId(teacher.getAisId());
                if(teacher.getInstitute() != null)
                    existingTeacher.setInstitute(teacher.getInstitute());
                if(teacher.getFinal_thesis() != null){
                    existingTeacher.setFinal_thesis(teacher.getFinal_thesis());
                }

                em.merge(existingTeacher);
                em.getTransaction().commit();
            }
            em.close();
            return existingTeacher;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        EntityManager em = emf.createEntityManager();
        List<Teacher> teachers;

        try {
            TypedQuery<Teacher> query = em.createQuery("SELECT s FROM Teacher s", Teacher.class);
            teachers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return teachers;
    }

    @Override
    public Teacher deleteTeacher(Long id) {
        if (id == null)
            throw new IllegalArgumentException();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Teacher teacher;

        try {
            teacher = em.find(Teacher.class, id);
            if(teacher == null)
                return null;
            em.remove(teacher);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            teacher = null;
        } finally {
            em.close();
        }
        return teacher;
    }

    @Override
    public Assignment makeThesisAssignment(Long supervisor, String title, String type, String description) {
        if (supervisor == null)
            throw new IllegalArgumentException("Supervisor ID cannot be null");
        if(title == null || type == null || description == null || getTeacher(supervisor) == null)
            return null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
            Assignment thesis = new Assignment();
            Teacher teacher = em.find(Teacher.class, supervisor);

            thesis.setName(title);
            thesis.setType(Type.valueOf(type));
            thesis.setDescription(description);
            thesis.setTeacher(teacher);

            thesis.setDate_release(LocalDate.now());
            thesis.setDate_deadline(thesis.getDate_release().plusMonths(3));
            thesis.setStatus(Status.FREE);

            em.persist(thesis);
            em.getTransaction().commit();
            em.close();

            em = emf.createEntityManager();
            em.getTransaction().begin();
            thesis.setReg_number(thesis.getId());
            em.merge(thesis);
            em.getTransaction().commit();
            em.close();
            return thesis;
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }

    @Override
    public Assignment assignThesis(Long thesisId, Long studentId) {
        if (thesisId == null || studentId == null) {
            throw new IllegalArgumentException("Thesis ID and student ID cannot be null.");
        }
        if(getThesis(thesisId) == null)
            return null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try{
            Assignment thesis = em.find(Assignment.class, thesisId);
            Student student = em.find(Student.class, studentId);
            if(student == null)
                return null;
            if (thesis.getStatus() == Status.OCCUPIED || thesis.getStatus() == Status.SUBMITTED) {
                throw new IllegalStateException("Thesis is not in a valid state for student assignment.");
            }
            thesis.setStudent(student);
            student.setFinal_thesis(thesisId);
            thesis.setStatus(Status.OCCUPIED);
            em.merge(thesis);
            em.getTransaction().commit();
            em.close();
            return thesis;
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }

    }

    @Override
    public Assignment submitThesis(Long thesisId) {
        if (thesisId == null) {
            throw new IllegalArgumentException("Thesis ID cannot be null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Assignment thesis = em.find(Assignment.class, thesisId);
            if ((thesis.getStatus() == Status.SUBMITTED) || (thesis.getStatus() == Status.FREE) || (thesis.getStudent() == null) || thesis.getDate_deadline().isBefore(LocalDate.now()))
                throw new IllegalStateException("Thesis is not in a valid state for student assignment.");

            thesis.setStatus(Status.SUBMITTED);
            em.merge(thesis);
            em.getTransaction().commit();
            return thesis;
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }

    @Override
    public Assignment deleteThesis(Long id) {
        if (id == null) throw new IllegalArgumentException();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Assignment thesis;

        try {
            thesis = em.find(Assignment.class, id);

            if (thesis.getStudent() != null) {
                Long studentId = thesis.getStudent().getAisId();
                Student student = em.find(Student.class, studentId);
                student.setFinal_thesis(null);
            }
            em.remove(thesis);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            thesis = null;
        } finally {
            em.close();
        }
        return thesis;
    }

    @Override
    public List<Assignment> getTheses() {
        EntityManager em = emf.createEntityManager();
        List<Assignment> assignments;

        try {
            TypedQuery<Assignment> query = em.createQuery("SELECT s FROM Assignment s", Assignment.class);
            assignments = query.getResultList();
            return assignments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Assignment> getThesesByTeacher(Long teacherId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Assignment> query = em.createQuery("SELECT s FROM Assignment s WHERE s.teacher.aisId = :teacherId", Assignment.class);
            query.setParameter("teacherId", teacherId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Assignment getThesisByStudent(Long studentId) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Assignment> query = em.createQuery("SELECT s FROM Assignment s WHERE s.student.aisId = :studentId", Assignment.class);
            query.setParameter("studentId", studentId);
            Assignment assignment;
            try {
                assignment = query.getSingleResult();
                return assignment;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Assignment getThesis(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Assignment> query = em.createQuery("SELECT s FROM Assignment s WHERE s.id = :id", Assignment.class);
            query.setParameter("id", id);
            Assignment assignment;
            if (query.getResultList() == null || query.getResultList().equals(""))
                return null;
            try {
                assignment = query.getSingleResult();
                return assignment;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Assignment updateThesis(Assignment thesis) {
        if (thesis == null || thesis.getId() == null) {
            throw new IllegalArgumentException();
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Assignment assignment = em.find(Assignment.class, thesis.getId());

            if (assignment != null) {
                if(thesis.getName() != null)
                    assignment.setName(thesis.getName());
                if(thesis.getDescription() != null)
                    assignment.setDescription(thesis.getDescription());
                if(thesis.getStudent() != null)
                    assignment.setStudent(thesis.getStudent());
                if(thesis.getStatus() != null)
                    assignment.setStatus(thesis.getStatus());
                if(thesis.getTeacher() != null)
                    assignment.setTeacher(thesis.getTeacher());
                if(thesis.getDate_release() != null)
                    assignment.setDate_release(thesis.getDate_release());
                if(thesis.getDescription() != null)
                    assignment.setDescription(thesis.getDescription());
                if(thesis.getWork_place() != null)
                    assignment.setWork_place(thesis.getWork_place());

                em.merge(assignment);
                em.getTransaction().commit();
            }
            em.close();
            return assignment;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();
            return null;
        }
    }
}
