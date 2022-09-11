import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class App {
    public static void main(String[] args) throws Exception 
    {
        JFrame loginFrame = new JFrame("Mass Monsters");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        loginFrame.setSize((int)size.getWidth(), (int)size.getHeight());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        loginFrame.add(panel);
        buildLoginFrame(loginFrame, panel);
        loginFrame.setVisible(true);
    }

    private static void buildLoginFrame(JFrame loginFrame, JPanel panel)
    {
        int componentWidth = 750;
        int componentHeight = 100;
        panel.setLayout(null);
        JLabel title = new JLabel("Welcome to Mass Monsters");
        title.setFont(new Font("Serif", Font.PLAIN, 50));
        title.setBounds(100, 100, componentWidth, componentHeight);
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        userLabel.setBounds(100, 225, componentWidth, componentHeight);
        panel.add(userLabel);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        passLabel.setBounds(100, 350, componentWidth, componentHeight);
        panel.add(passLabel);

        JTextField userField = new JTextField(30);
        userField.setFont(new Font("Serif", Font.PLAIN, 50));
        userField.setBounds(componentWidth + 100, 225, componentWidth, componentHeight);
        panel.add(userField);

        JTextField passField = new JTextField(30);
        passField.setFont(new Font("Serif", Font.PLAIN, 50));
        passField.setBounds(componentWidth + 100, 350, componentWidth, componentHeight);
        panel.add(passField);
        
        JButton loginButton = new JButton("Get Big!");
        loginButton.setFont(new Font("Serif", Font.PLAIN, 50));
        loginButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            login(loginFrame, userField.getText(), passField.getText());
        }});
        loginButton.setBounds(100, 475, componentWidth, componentHeight);
        panel.add(loginButton);

        JButton accountButton = new JButton("Make Account?");
        accountButton.setFont(new Font("Serif", Font.PLAIN, 50));
        accountButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            buildAccountFrame();
        }});
        accountButton.setBounds(componentWidth + 100, 475, componentWidth, componentHeight);
        panel.add(accountButton);
    }

    private static void login(JFrame frame, String username, String password)
    {
        Bridge b = new Bridge();
         try
         {
             if(b.SQLstatementExists("SELECT COUNT(1) FROM userProfile WHERE username = '" + username + "' AND userpassword = '" + password + "';") == 1)
             {
                frame.setVisible(false);
                frame.dispose();
                buildHomeFrame(username, b);
             }
             else
             {
                 JFrame errorFrame = new JFrame("Login Failed");
                 errorFrame.setSize(500, 200);
                 JLabel failed = new JLabel("Login has failed. Please try again.");
                 failed.setFont(new Font("Serif", Font.BOLD, 25));
                 failed.setBounds(10, 10, 50, 100);
                 errorFrame.add(failed);
                 errorFrame.setAlwaysOnTop(true);
                errorFrame.setVisible(true);
             }
          }
          catch(Exception e)
          {
             System.out.println("Database broke");
          }
    }

    private static void buildHomeFrame(String username, Bridge b)
    {
        int componentWidth = 350;
        int componentHeight = 40;
        JFrame homeFrame = new JFrame("Mass Monsters");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        homeFrame.setSize((int)size.getWidth(), (int)size.getHeight());
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel homePanel = new JPanel();
        homeFrame.add(homePanel);
        homePanel.setLayout(null);

        JLabel title = new JLabel("Mass Monsters");
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setBounds(0, 10, componentWidth, componentHeight);
        homePanel.add(title);

        JLabel userLabel = new JLabel(username);
        userLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        userLabel.setBounds(0, componentHeight + 25, componentWidth, componentHeight);
        homePanel.add(userLabel);
        
        JButton addWorkout = new JButton("Add Workout");
        addWorkout.setFont(new Font("Serif", Font.PLAIN, 30));
        addWorkout.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            addWorkout(b);
        }});
        addWorkout.setBounds(componentWidth + 10, componentHeight + 25, componentWidth, componentHeight);
        homePanel.add(addWorkout);
        
        JButton viewWorkouts = new JButton("View Workouts");
        viewWorkouts.setFont(new Font("Serif", Font.PLAIN, 30));
        viewWorkouts.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            try
            {
                displayWorkouts(b);
            }
            catch(Exception problem)
            {
                System.out.println(problem.getMessage());
            }
        }});
        viewWorkouts.setBounds(componentWidth*2 + 10, componentHeight + 25, componentWidth, componentHeight);
        homePanel.add(viewWorkouts);
        homeFrame.setVisible(true);
    }

    private static void addWorkout(Bridge b)
    {
        int componentHeight = 40;
        int componentWidth = 300;
        
        JFrame workoutFrame = new JFrame();
        workoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        workoutFrame.setSize((int)size.getWidth()/2, (int)size.getHeight()/2);
        
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(null);
        workoutFrame.add(workoutPanel);
        workoutFrame.setAlwaysOnTop(true);
        
        JLabel name = new JLabel("Exercise Name:");
        name.setFont(new Font("Serif", Font.PLAIN, 30));
        name.setBounds(10, 10, componentWidth, componentHeight);
        workoutPanel.add(name);

        JLabel muscle = new JLabel("Targeted Muscle:");
        muscle.setFont(new Font("Serif", Font.PLAIN, 30));
        muscle.setBounds(10, componentHeight + 10, componentWidth, componentHeight);
        workoutPanel.add(muscle);

        JLabel day = new JLabel("Day of Week (1-7):");
        day.setFont(new Font("Serif", Font.PLAIN, 30));
        day.setBounds(10, componentHeight*2 + 10, componentWidth, componentHeight);
        workoutPanel.add(day);

        JLabel sets = new JLabel("Amount of Sets:");
        sets.setFont(new Font("Serif", Font.PLAIN, 30));
        sets.setBounds(10, componentHeight*3 + 10, componentWidth, componentHeight);
        workoutPanel.add(sets);

        JLabel reps = new JLabel("Amount of Reps:");
        reps.setFont(new Font("Serif", Font.PLAIN, 30));
        reps.setBounds(10, componentHeight*4 + 10, componentWidth, componentHeight);
        workoutPanel.add(reps);

        JTextField nameField = new JTextField(30);
        nameField.setFont(new Font("Serif", Font.PLAIN, 30));
        nameField.setBounds(componentWidth + 10, 10, componentWidth, componentHeight);
        workoutPanel.add(nameField);

        JTextField muscleField = new JTextField(30);
        muscleField.setFont(new Font("Serif", Font.PLAIN, 30));
        muscleField.setBounds(componentWidth + 10, componentHeight + 10, componentWidth, componentHeight);
        workoutPanel.add(muscleField);

        JTextField dayField = new JTextField(30);
        dayField.setFont(new Font("Serif", Font.PLAIN, 30));
        dayField.setBounds(componentWidth + 10, componentHeight*2 + 10, componentWidth, componentHeight);
        workoutPanel.add(dayField);

        JTextField setsField = new JTextField(30);
        setsField.setFont(new Font("Serif", Font.PLAIN, 30));
        setsField.setBounds(componentWidth + 10, componentHeight*3 + 10, componentWidth, componentHeight);
        workoutPanel.add(setsField);

        JTextField repsField = new JTextField(30);
        repsField.setFont(new Font("Serif", Font.PLAIN, 30));
        repsField.setBounds(componentWidth + 10, componentHeight*4 + 10, componentWidth, componentHeight);
        workoutPanel.add(repsField);

        JButton addButton = new JButton("Save Workout");
        addButton.setFont(new Font("Serif", Font.PLAIN, 30));
        addButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            if(!(nameField.getText().isEmpty() || muscleField.getText().isEmpty() || dayField.getText().isEmpty() || setsField.getText().isEmpty() || repsField.getText().isEmpty()))
            {
                try
                {
                    Exercise ex = new Exercise(nameField.getText(), muscleField.getText(), Integer.parseInt(dayField.getText()) - 1, Integer.parseInt(setsField.getText()), Integer.parseInt(repsField.getText()));
                    int val = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = " + ex.getDay() + ";");
                    b.SQLstatementUpdate("INSERT INTO exercises(exerciseId, exerciseName, muscleGroup, daysOfWeek, setNum, repNum)VALUES(" + (1+val) + ", '" + ex.getName() + "', '" + ex.getMuscle() + "', " + ex.getDay() + ", " + ex.getSetNum() + ", " + ex.getRepNum() + ");");
                    workoutFrame.dispose();
                }
                catch(Exception excep)
                {
                    System.out.println("Numbers broke");
                }
            }
            else
            {
                workoutFrame.dispose();
            }
        }});
        addButton.setBounds(10, componentHeight*5 + 10, componentWidth, componentHeight);
        workoutPanel.add(addButton);
        workoutFrame.setVisible(true);
    }
    
    private static void displayWorkouts(Bridge b) throws SQLException
    {    
        int[] numExPerDay = new int[7];
        numExPerDay[0] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 0;"); 
        numExPerDay[1] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 1;"); 
        numExPerDay[2] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 2;"); 
        numExPerDay[3] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 3;"); 
        numExPerDay[4] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 4;"); 
        numExPerDay[5] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 5;"); 
        numExPerDay[6] = b.SQLstatementExists("SELECT COUNT(*) FROM exercises WHERE daysOfWeek = 6;"); 

        int max = -1;
        for(int i = 0; i < 7; i++)
        {
            if(numExPerDay[i] > max)
            max = numExPerDay[i];
        }

        int componentWidth = 200;
        int componentHeight = 40;
        JFrame savedFrame = new JFrame();
        savedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        savedFrame.setSize((int)size.getWidth(), (int)size.getHeight());
        
        JPanel savedPanel = new JPanel();
        savedPanel.setLayout(null);
        savedFrame.add(savedPanel);
        savedFrame.setAlwaysOnTop(true);

        JLabel monday = new JLabel("Monday");
        monday.setFont(new Font("Serif", Font.PLAIN, 30));
        monday.setBounds(10, 10, componentWidth, componentHeight);
        savedPanel.add(monday);
        ArrayList<JLabel> exercises = new ArrayList<JLabel>();
        ArrayList<String> exerciseNames = new ArrayList<String>();
       
        for(int i = 1; i <= numExPerDay[0]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 0 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[0]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel tuesday = new JLabel("Tuesday");
        tuesday.setFont(new Font("Serif", Font.PLAIN, 30));
        tuesday.setBounds(componentWidth + 10, 10, componentWidth, componentHeight);
        savedPanel.add(tuesday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[1]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 1 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[1]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel wednesday = new JLabel("Wednesday");
        wednesday.setFont(new Font("Serif", Font.PLAIN, 30));
        wednesday.setBounds(componentWidth*2 + 10, 10, componentWidth, componentHeight);
        savedPanel.add(wednesday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[2]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 2 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[2]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth*2 + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel thursday = new JLabel("Thursday");
        thursday.setFont(new Font("Serif", Font.PLAIN, 30));
        thursday.setBounds(componentWidth*3 + 10, 10, componentWidth, componentHeight);
        savedPanel.add(thursday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[3]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 3 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[3]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth*3 + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel friday = new JLabel("Friday");
        friday.setFont(new Font("Serif", Font.PLAIN, 30));
        friday.setBounds(componentWidth*4 + 10, 10, componentWidth, componentHeight);
        savedPanel.add(friday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[4]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 4 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[4]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth*4 + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel saturday = new JLabel("Saturday");
        saturday.setFont(new Font("Serif", Font.PLAIN, 30));
        saturday.setBounds(componentWidth*5 + 10, 10, componentWidth, componentHeight);
        savedPanel.add(saturday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[5]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 5 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[5]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth*5 + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        JLabel sunday = new JLabel("Sunday");
        sunday.setFont(new Font("Serif", Font.PLAIN, 30));
        sunday.setBounds(componentWidth*6 + 10, 10, componentWidth, componentHeight);
        savedPanel.add(sunday);
        exercises = new ArrayList<JLabel>();
        exerciseNames = new ArrayList<String>();
        for(int i = 1; i <= numExPerDay[6]; i++)
        {
            exerciseNames.add(b.SQLstatementSelect("SELECT exerciseName FROM exercises WHERE daysOfWeek = 6 AND exerciseId = "  + i + ";", "exerciseName"));
            exercises.add(new JLabel(exerciseNames.get(i-1)));
        }
        for(int i = 1; i <= numExPerDay[6]; i++)
        {
            exercises.get(i-1).setFont(new Font("Serif", Font.PLAIN, 30));
            exercises.get(i-1).setBounds(componentWidth*6 + 10, componentHeight*(i)+20, componentWidth, componentHeight);
            savedPanel.add(exercises.get(i-1));
        }

        savedFrame.setVisible(true);
    }

    private static void buildAccountFrame()
    {
        int componentHeight = 40;
        int componentWidth = 300;
        JFrame accountFrame = new JFrame();
        accountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        accountFrame.setSize((int)size.getWidth()/2, (int)size.getHeight()/2);

        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(null);
        accountFrame.add(accountPanel);
        accountFrame.setAlwaysOnTop(true);

        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("Serif", Font.PLAIN, 30));
        firstName.setBounds(10, 10, componentWidth, componentHeight);
        accountPanel.add(firstName);

        JTextField firstField = new JTextField(30);
        firstField.setFont(new Font("Serif", Font.PLAIN, 30));
        firstField.setBounds(componentWidth + 10, 10, componentWidth, componentHeight);
        accountPanel.add(firstField);

        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("Serif", Font.PLAIN, 30));
        lastName.setBounds(10, componentHeight + 10, componentWidth, componentHeight);
        accountPanel.add(lastName);

        JTextField lastField = new JTextField(30);
        lastField.setFont(new Font("Serif", Font.PLAIN, 30));
        lastField.setBounds(componentWidth + 10, componentHeight + 10, componentWidth, componentHeight);
        accountPanel.add(lastField);

        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Serif", Font.PLAIN, 30));
        email.setBounds(10, componentHeight*2 + 10, componentWidth, componentHeight);
        accountPanel.add(email);

        JTextField emailField = new JTextField(30);
        emailField.setFont(new Font("Serif", Font.PLAIN, 30));
        emailField.setBounds(componentWidth + 10, componentHeight*2 + 10, componentWidth, componentHeight);
        accountPanel.add(emailField);

        JLabel phone = new JLabel("Phone Number:");
        phone.setFont(new Font("Serif", Font.PLAIN, 30));
        phone.setBounds(10, componentHeight*3 + 10, componentWidth, componentHeight);
        accountPanel.add(phone);

        JTextField phoneField = new JTextField(30);
        phoneField.setFont(new Font("Serif", Font.PLAIN, 30));
        phoneField.setBounds(componentWidth + 10, componentHeight*3 + 10, componentWidth, componentHeight);
        accountPanel.add(phoneField);
        
        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Serif", Font.PLAIN, 30));
        username.setBounds(10, componentHeight*4 + 10, componentWidth, componentHeight);
        accountPanel.add(username);

        JTextField userField = new JTextField(30);
        userField.setFont(new Font("Serif", Font.PLAIN, 30));
        userField.setBounds(componentWidth + 10, componentHeight*4 + 10, componentWidth, componentHeight);
        accountPanel.add(userField);

        JLabel pass = new JLabel("Password:");
        pass.setFont(new Font("Serif", Font.PLAIN, 30));
        pass.setBounds(10, componentHeight*5 + 10, componentWidth, componentHeight);
        accountPanel.add(pass);

        JTextField passField = new JTextField(30);
        passField.setFont(new Font("Serif", Font.PLAIN, 30));
        passField.setBounds(componentWidth + 10, componentHeight*5 + 10, componentWidth, componentHeight);
        accountPanel.add(passField);

        JButton makeAccountButton = new JButton("Make Account");
        makeAccountButton.setFont(new Font("Serif", Font.PLAIN, 30));
        makeAccountButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
            if(!(userField.getText().equals("") || passField.getText().equals("") || emailField.getText().equals("") || firstField.getText().equals("") || lastField.getText().equals("") || phoneField.getText().equals("")))
            {
                try
                {
                    Profile profile = new Profile(userField.getText(), passField.getText(), emailField.getText(), firstField.getText(), lastField.getText(), Long.parseLong(phoneField.getText()));
                    Bridge b = new Bridge();
                    profileCreation(b, profile);
                    accountFrame.dispose();
                }
                catch(Exception exception)
                {
                    System.out.println("Account broke");
                }
            }
        }});
        makeAccountButton.setBounds(10, componentHeight*6 + 10, componentWidth, componentHeight);
        accountPanel.add(makeAccountButton);
        accountFrame.setVisible(true);
    }

    public static void profileCreation(Bridge b, Profile p) throws SQLException{
        b.SQLstatementUpdate("INSERT INTO userProfile (username, userPassword, emailAddress, firstName, lastName, phoneNumber) VALUES ('" + p.getUsername() + "', '" + p.getPassword() + "', '" + p.getEmailAddress() + "', '" + p.getFirstName() + "', '" + p.getLastName() + "', " + p.getPhoneNum() + ");");
    }
}
