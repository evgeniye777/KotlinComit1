import java.util.ArrayList;
import java.util.List;

public class proofOfSkills {
    public static void main(String[] args) {
    }
    private UserManager userManager = new UserManager();

    public String processInput(String commandLine) {
        String[] masPerson = commandLine.split(",");
        for (String onePerson: masPerson) {
            String[] command = onePerson.split(" ");
            if (command[0].equals("add_user")) {
                if (command.length==3) {
                    userManager.addUser(command[1],Integer.parseInt(command[2]));
                }
                else {userManager.addUser(command[1]);}
            }
            else if (command[0].equals("remove_user")) { for (User u: userManager.users) {}}
            else if (command[0].equals("promote")) {}
            else if (command[0].equals("demote")) {}
            else if (command[0].equals("get_users ")) {}
        }
        return null;
    }
}

class UserManager {
    List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public UserManager addUser (String name, int accessLevel) {
        users.add(new User(name, accessLevel));
        return this;
    }

    public UserManager addUser (String name) {
        return addUser (name, 1); // Уровень доступа по умолчанию равен 1
    }

    public UserManager removeUser (String name) {
        users.removeIf(user -> user.getName().equals(name));
        return this;
    }

    public UserManager promote(String name) {
        for (User  user : users) {
            if (user.getName().equals(name)) {
                user.setAccessLevel(user.getAccessLevel() + 1);
                break;
            }
        }
        return this;
    }

    public UserManager demote(String name) {
        for (User  user : users) {
            if (user.getName().equals(name) && user.getAccessLevel() > 0) {
                user.setAccessLevel(user.getAccessLevel() - 1);
                break;
            }
        }
        return this;
    }

    public String getUsers() {
        if (users.isEmpty()) {
            return "Не найдено";
        }
        StringBuilder result = new StringBuilder();
        for (User  user : users) {
            result.append(user.toString()).append("\n");
        }
        return result.toString().trim(); // Убираем последний перенос строки
    }
}

class User {
    private String name;
    private int accessLevel;

    public User(String name, int accessLevel) {
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int i) {
        accessLevel = i;
    }
}
