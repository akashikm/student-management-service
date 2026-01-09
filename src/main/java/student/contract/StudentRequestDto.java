package student.contract;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String name;
    @Min(value = 3, message = "Age must be at least 3")
    @Max(value = 30, message = "Age must be at most 30")
    private Integer age;
    private String course;
    @Email(message = "Email must be valid")
    private String email;
}

