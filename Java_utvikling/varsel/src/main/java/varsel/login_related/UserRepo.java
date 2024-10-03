package varsel.login_related;

// Path: varsel/src/main/java/varsel/login_related/UserRepo.java
// Use-case: UserRepo.java is an interface that provides methods to interact with the database.
// Methods: findUserById(double user)
// Dependencies: User.java

public interface UserRepo {
    User findUserById(double user_id);
}

