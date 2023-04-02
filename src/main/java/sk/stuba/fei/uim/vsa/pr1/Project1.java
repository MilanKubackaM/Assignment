package sk.stuba.fei.uim.vsa.pr1;

import java.util.List;

public class Project1 {
    static private final Handler handler = new Handler();

    public static Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) {
        // Handler handler = new Handler();


// =================================STUDENT======================================  //

        // >>>>>> CREATE STUDENT <<<<<
        handler.createStudent(256L, "Sano Prvy", "created1@stuba.sk");
        handler.createStudent(255L, "Miro", "created2@stuba.sk");
        handler.createStudent(1L, "Denis Treti", "created3@stuba.sk");

        // >>>>>> UPDATE STUDENT <<<<<
        Student student = new Student();
        student.setAisId(256L);
        student.setName("Milan Kubacka");
        student.setEmail("totoJeUpdate@stuba.sk");
        handler.updateStudent(student);

        // >>>>>> GET STUDENTS <<<<<<
        List<Student> allStudents = handler.getStudents();
        System.out.println("Get all students name:");
        for(Student allStudent : allStudents){
            System.out.println(allStudent.getName());
        }

        // >>>>>> DELETE STUDENT <<<<<
        Long idForDeleteStudent = 256L;
        handler.assignThesis(1L, 256L);
        System.out.println("Deleted student: " + handler.deleteStudent(idForDeleteStudent));

// ================================TEACHER=========================================  //


        // >>>>> CREATE TEACHER <<<<<<
        Long teacherID = 123L;
        handler.createTeacher(teacherID, "Peter Prvy", "emailucitelaprvy@stuba.sk", "SAV");
        handler.createTeacher(122L, "Fero Druhy", "emailuciteladruhy@stuba.sk", "LAV");
        handler.createTeacher(121L, "Vlado Treti", "emailucitelatreti@stuba.sk", "DAV");

        // >>>>> GET TEACHER <<<<<<
//        System.out.println("Get teacher: " + handler.getTeacher(teacherID));

        // >>>>> UPDATE TEACHER <<<<<
//        Teacher teacher = new Teacher();
//        teacher.setAisId(123L);
//        teacher.setName("Peter updated");
//        teacher.setEmail("totoJeUpdate@gmail.com");
//        handler.updateTeacher(teacher);
//        System.out.println("Updated teacher: " + handler.updateTeacher(teacher));

        // >>>>> GET TEACHERS <<<<<<
//        List<Teacher> allTeachers = handler.getTeachers();
//        System.out.println("Get all teachers name:");
//        for(Teacher allTeacher : allTeachers){
//            System.out.println(allTeacher.getName());
//        }
//
//        // >>>>> DELETE TEACHER <<<<<
//        Long idForDeleteTeacher = 121L;
//        System.out.println("Deleted teacher: " + handler.deleteTeacher(idForDeleteTeacher));

// ==================================THESIS=======================================  //

        // >>>>> MAKE THESIS ASSINGMENT <<<<<
        Long teacherId = 123L;
        handler.makeThesisAssignment(teacherId, "", "BACHELOR", "Navrh, develop a deployement projektu");
        handler.makeThesisAssignment(teacherId, "Instalacia Ineho niecoho", "MASTER", "Navrh, develop a deployement projektu");
        handler.makeThesisAssignment(122L, "Nieco ine", "BACHELOR", "Zasa neico ine");
//
//
//        // >>>>> ASSIGN THESIS <<<<<
        Long thesisId = 1L;
        Long studentId = 256L;
        handler.assignThesis(thesisId, studentId);

        // >>>>> SUBMIT THESIS <<<<<
//        handler.submitThesis(1L);

        // >>>>> DELETE THESIS <<<<<
//        handler.deleteThesis(1L);

        // >>>>> GET THESES <<<<<
//        handler.getTheses();

        // >>>>> GET THESES BY TEACHER <<<<<
//        System.out.println("Pole thesis podla ucitela: " + handler.getThesesByTeacher(teacherID));

        // >>>>> GET THESIS BY STUDENT <<<<<
//        System.out.println("Thesis podla studenta: " + handler.getThesisByStudent(idForDeleteStudent).getName());

        // >>>>> GET THESIS <<<<<
//        System.out.println("Thesis podla ID: " + handler.getThesis(2L).getId() + " meno: " +  handler.getThesis(2L).getName());

        // >>>>> UPDATE THESIS <<<<<
//        Assignment thesis = new Assignment();
//        thesis.setId(2L);
//        thesis.setName("NiecoIne");
//        thesis.setDescription("Taktiezniecoine");
//        handler.updateThesis(thesis);


        // >>>>>>> OTHERS <<<<<<<<
//        System.out.println("Deleted student: " + handler.deleteStudent(idForDeleteStudent));



    }

}
