@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {

        User user = User.builder()
                .email(body.get("email"))
                .password(body.get("password"))
                .role(User.Role.STUDENT)   // default role
                .build();

        return userService.register(user);
    }
}
