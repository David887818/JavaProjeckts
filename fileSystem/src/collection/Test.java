package collection;
import collection.io.SerializeUtilUser;
import collection.model.*;
import collection.storage.CommentStorage;
import collection.storage.PostStorage;
import collection.storage.UserStorage;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
public class Test implements CommandsForBlogs {
    private static Scanner scn = new Scanner(System.in);
    private static UserStorage userStorage = new UserStorage();
    private static PostStorage postStorage = new PostStorage();
    private static CommentStorage commentStorage = new CommentStorage();
    private static User currentUser;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User("name", "surname", "email", "password");
        userStorage.put(user.getName(), user);
        Comment comments = new Comment("text", user, new Date());
        Post post = new Post("title", "post", Categorie.IT, new Date(), user, comments);
        commentStorage.put(post.getTitle(), comments);
        postStorage.add(post);
        startingBlog();
    }
    private static void startingBlog() throws IOException, ClassNotFoundException {
        boolean isRun = true;
        int s = 0;
        while (isRun) {
            generalCommands();
            try {
                s = Integer.parseInt(scn.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("invalid command type ");
                startingBlog();
            }
            switch (s) {
                case EXIT:
                    isRun = false;
                    break;
                case POSTS_BY_CATEGORY:
                    getCategoryByName();
                    break;
                case POSTS_BY_USER:
                    postsByUser();
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case ADD_COMMENT:
                    addComments();
                    break;
                case PRINT_POSTS:
                    postStorage.printPostLists();
                    break;
            }
        }
    }
    private static void login() {
        System.out.println("Please input email,password for logIn");
        String loginDataStr = scn.nextLine();
        String[] split = loginDataStr.split(",");
        if (split.length == 2) {
            try {
                currentUser = userStorage.getUserByEmailAndPassword(split[0], split[1]);
                System.out.println("Welcome " + currentUser.getName());
                try {
                    startingUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MyException e) {
                System.err.println("User not found ");
            }
        } else {
            System.out.println("please input correctly ");
            try {
                startingBlog();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private static void register(){
        System.out.println("Please input name,surname,email,password");
        String userStr = scn.nextLine();
        String[] userData = userStr.split(",");
        if (userData.length == 4) {
            if (userStorage.isEmailExist(userData[2])) {
                System.out.println("Username already exist");
                register();
            } else {
                User user = new User(userData[0], userData[1], userData[2], userData[3]);
                userStorage.put(user.getName(), user);
                System.out.println("Thank you, you have successfully registered");
            }
        } else {
            System.out.println("please normal input");
        }
    }
    private static void addComments()  {
        Post post = null;
        postStorage.printPostLists();
        System.out.println("Please input Your Post title for Adding Comment");
        String str = scn.nextLine();
        try {
            post = postStorage.getPostByTitle(str);
        } catch (MyException e) {
            System.err.println("Post not found ");
            try {
                startingUser();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("Please write comment");
        String s = scn.nextLine();
        Comment comments = new Comment();
        comments.setText(s);
        comments.setUser(currentUser);
        comments.setDate(new Date());
        commentStorage.put(post.getTitle(), comments);
        if (post != null)
            post.setComments(comments);
        System.out.println("Thanks for adding comment");
        System.out.println("\n" + "POST TITLE " + "\n" + "  " + post.getTitle() + "\n" + "COMMENTS..." + "\n" + post.getComments().getText() + " ");
        if (currentUser != null) {
            System.out.println("        " + "User name - " + post.getComments().getUser().getEmail() + "\n");
        }
    }
    private static void postsByUser() {
        System.out.println("Please input user email for searching ");
        try {
            if (currentUser != null) {
                postStorage.postByUser(currentUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getPostByCategoryName(String name) {
        System.out.println("please input category name for searching ");
        postStorage.searchByCategory(name);
    }
    private static void startingUser() throws IOException {
        boolean isRun = true;
        int s = 0;
        while (isRun) {
            userCommands();
            try {
                s = Integer.parseInt(scn.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("input correctly ");
            }
            switch (s) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPosts();
                    break;
                case DELETE_POST:
                    deletePosts();
                    break;
                case PRINT_USERS:
                    userStorage.printUserList();
                    break;
            }
        }
    }
    private static void addPosts()  {
        System.out.println("Please input your post`s title, post,categoryType");
        String str = scn.nextLine();
        String[] split = str.split(",");
        Post post = new Post();
        if (split.length == 3) {
            post.setTitle(split[0]);
            post.setPost(split[1]);
            try {
                post.setCategories(Categorie.valueOf(split[2].toUpperCase()));
            } catch (IllegalArgumentException i) {
                System.err.println("Illegal Category type");
                try {
                    startingUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            post.setCreatedDate(new Date());
            post.setUser(currentUser);
            postStorage.add(post);

            System.out.println("Thanks your post is added");
        } else {
            System.out.println("please complete all lines ");
        }
    }
    private static void getCategoryByName() throws IOException, ClassNotFoundException {
        boolean isRun = true;
        int s = 0;
        while (isRun) {
            categoryCommands();
            try {
                s = Integer.parseInt(scn.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("invalid command type ");
                startingBlog();
            }
            switch (s) {
                case EXIT:
                    isRun = false;
                    break;
                case ENUM_IT:
                    getPostByCategoryName("IT");
                    break;
                case ENUM_ART:
                    getPostByCategoryName("ART");
                    break;
                case ENUM_SOCIAL:
                    getPostByCategoryName("SOCIAL");
                    break;

            }
        }
    }
    private static void deletePosts() {
        System.out.println("Please input your post title for removing ");
        postStorage.printPostLists();
        String s = scn.nextLine();
        postStorage.deletePost(s);
    }
    private static void generalCommands() {
        System.out.println("input 0 for EXIT");
        System.out.println("input 1 for POSTS BY CATEGORY");
        System.out.println("input 2 for POSTS BY USER");
        System.out.println("input 3 for LOGIN");
        System.out.println("input 4 for REGISTER");
        System.out.println("input 5 for ADDING COMMENTS");
        System.out.println("input 6 for PRINTING POSTS");
    }
    private static void userCommands() {
        System.out.println("input 0 for LogOut ");
        System.out.println("input 1 for addPosts");
        System.out.println("input 2 for Delete post");
        System.out.println("input 3 for print users");

    }
    private static void categoryCommands() {
        System.out.println("0 for EXIT");
        System.out.println("1 for IT");
        System.out.println("2 for ART");
        System.out.println("3 for SOCIAL");
    }
}

