package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.model.UserSession;
import com.awedzonka.bhpbackend.repository.UserRepository;
import com.awedzonka.bhpbackend.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class UserService {

    private final static String REGEX = "\\W+";

    private UserRepository userRepository;
    private UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    //rejestracja
    public String checkLoginAndPassword(User user) {
        long value = userRepository.countByLogin(user.getLogin());
        if (value != 0) {
            generateUniqueLoginForUser(user);
            return "Login o podanej nazwie już istnieje, podaj inny login lub wykorzystaj zasugerowany login: " + user.getLogin();
        }
        String login = user.getLogin();
        if (login.length() != login.replaceAll(" ", "").length()) {
            return "Login nie może mieć spacji";
        }
        //regex login
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(login);
        if (matcher.find() == true) {
            return "Login może zawierać tylko małe i duże litery, podkreślnik oraz cyfry.";
        }

        if (!user.getPassword().equals(user.getPassword2())) {
            return "Hasła muszą być takie same";
        }
        return "registrationSuccess";
    }

    private String generateUniqueLoginForUser(User user) {
        String login = user.getLogin();
        int counter = 1;
        while (userRepository.countByLogin(login + counter) > 0) {
            counter++;
        }
        user.setLogin(login + counter);
        return login + counter;
    }

    // login
    public String checkLogin(String login, String password, Model model) {
        if (login.length() == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Podaj login");
            return "Podaj login";
        }
        if (password.length() == 0) {
            model.addAttribute("passInvalid", true);
            model.addAttribute("messagePass", "Podaj hasło");
            return "Podaj hasło";
        }
        if (userRepository.countByLogin(login) == 0) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Błąd logowania, zły login lub hasło");
            return "Błąd logowania, zły login lub hasło";
        }
        if (userRepository.countByLogin(login) > 1) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Duplikacja loginów, skontaktuj się w administratorem");
            return "Duplikacja loginów, skontaktuj się w administratorem";
        }

        User user = userRepository.findUserByLogin(login);
        if (!BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("loginInvalid", true);
            model.addAttribute("messageLogin", "Błąd logowania, zły login lub hasło");
            return "Podaj prawidłowe hasło";

        }

        return "loginSuccess";
    }

    // zapis użytkownika do sesji
    public void sessionStart(String login) {
        userSession.setUserInSession(userRepository.findUserByLogin(login));
        userSession.setLoggedUser(true);
    }

    public User getUserSession() {
        return userSession.getUserInSession();
    }

    // szukanie użytkownika
    public List<User> searchUser(String search) {
        return userRepository.findUserByLastNameContainingOrFirstNameContainingOrLoginContaining(search, search, search);
    }

    public List<User> examPassed(Boolean passed) {
        return userRepository.findUserByExamPassed(passed);
    }

    public String percentageOfPassedExams() {
        float result = (examPassed(true).size() / (float) findAll().size()) * 100f;
        if (result == 0) {
            return "0.00";
        }
        return new DecimalFormat("##.00").format(result);
    }

    public long quantitySuperUsers() {
        return userRepository.countBySuperUser(true);
    }

    public void setSlideNumberInLoggedUser(int slideNumber) {
        if (userSession.isLoggedUser()) {
            User userInSession = userSession.getUserInSession();
            User userFromDB = findUserById(userInSession.getId());
            userFromDB.setLastSlide(slideNumber);
            save(userFromDB);
        }
    }

    public String saveEditUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        // uzupełnienie pozostałych danych
        User userFromDB = findUserById(user.getId());
        user.setLogin(userFromDB.getLogin());
        user.setPassword(userFromDB.getPassword());
        user.setLastTestTime(userFromDB.getLastTestTime());
        user.setExamPassed(userFromDB.isExamPassed());
        user.setSuperUser(userFromDB.isSuperUser());

        save(user);
        userSession.setUserInSession(user);// zaktualizowanie
        model.addAttribute("changes", true);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("message", "Profil zaktualizowany");
        return "/home";
    }

    public String status(Model model) {
        User user = userSession.getUserInSession();
        model.addAttribute("status", user.isExamPassed());

        LocalDateTime date = user.getLastTestTime();
        if (date != null) {

            String lastTime = date.getYear() + "-"
                + getCorrectFormat(date.getMonthValue()) + "-"
                + getCorrectFormat(date.getDayOfMonth()) + " godzina: "
                + getCorrectFormat(date.getHour()) + ":"
                + getCorrectFormat(date.getMinute());

            model.addAttribute("time", lastTime);
        }
        return "/users/status";
    }

    private String getCorrectFormat(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }
}
