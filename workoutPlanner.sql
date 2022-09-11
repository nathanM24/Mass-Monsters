DATABASE workoutplanner;
USE workoutplanner;
CREATE TABLE userProfile(
	username VARCHAR(20),
    userPassword VARCHAR(20),
    emailAddress VARCHAR(20),
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    phoneNumber INT,
    PRIMARY KEY (username,userPassword)
);

CREATE TABLE exercises(
	exerciseId INT,
	exerciseName VARCHAR(20),
    muscleGroup VARCHAR(20),
    daysOfWeek INT,
    setNum INT,
    repNum INT
);


